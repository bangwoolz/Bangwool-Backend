package bangwool.server.service;

import bangwool.server.domain.Member;
import bangwool.server.domain.Platform;
import bangwool.server.dto.KakaoUser;
import bangwool.server.dto.request.AuthLoginRequest;
import bangwool.server.dto.request.KakaoLoginRequest;
import bangwool.server.dto.response.KakaoMemberInfoResponse;
import bangwool.server.dto.response.KakaoTokenResponse;
import bangwool.server.dto.response.OAuthTokenResponse;
import bangwool.server.dto.response.TokenResponse;
import bangwool.server.exception.badreqeust.PasswordMismatchException;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${kakao.api-key}") String clientId;
    @Value("${kakao.redirect-uri}") String redirectUri;
    @Value("${kakao.client-id}") String clientSecret;


    public TokenResponse login(AuthLoginRequest request) {
        Member findMember = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(NotFoundMemberException::new);
        validatePassword(findMember, request.getPassword());

        String token = issueToken(findMember);

        return TokenResponse.from(token);
    }

    private void validatePassword(final Member findMember, final String password) {
        if (!passwordEncoder.matches(password, findMember.getPassword())) {
            throw new PasswordMismatchException();
        }
    }

    private String issueToken(final Member findMember) {
        return jwtTokenProvider.createToken(findMember.getId());
    }

    public String getKakaoToken(String code) {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.set("grant_type", "authorization_code");
        param.set("client_id", clientId);
        param.set("redirect_uri", redirectUri);
        param.set("code", code);

        HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(param, headers);
        ResponseEntity<KakaoTokenResponse> apiResponse =
                restTemplate.postForEntity(
                        "https://kauth.kakao.com/oauth/token",
                        restRequest,
                        KakaoTokenResponse.class);
        KakaoTokenResponse responseBody = apiResponse.getBody();


        System.out.println("response.toString() = " + responseBody.getAccess_token());
        return responseBody.getAccess_token();


    }

//
    public KakaoMemberInfoResponse getKakaoMemberInfo(KakaoLoginRequest kakaoLoginRequest) {
        //카카오에 요청 보내기 및 응답 받기
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        // 사용자 정보 가져오기 : 이메일, 프로필 사진, 닉네임\
        KakaoUser response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v2/user/me").build())
                .header("Authorization", "Bearer " + kakaoLoginRequest.getToken())
                .retrieve().bodyToMono(KakaoUser.class).block();
        return KakaoMemberInfoResponse.of(response);
    }

    public OAuthTokenResponse kakaoLogin(KakaoMemberInfoResponse memberInfo) {
        String email = memberInfo.getEmail();
        Long platformId = memberInfo.getId();

        return memberRepository.findIdByPlatformAndPlatformId(memberInfo.getPlatform(), memberInfo.getId())
                .map(memberId -> {
                    Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
                    String token = issueToken(member);
                    return new OAuthTokenResponse(token,  Platform.KAKAO, platformId, member.getId());
                })
                .orElseGet(() -> {
                    Member member = Member.builder()
                            .platform(memberInfo.getPlatform())
                            .platformId(memberInfo.getId())
                            .email(memberInfo.getEmail())
                            .build();
                    Member savedMember = memberRepository.save(member);
                    String token = issueToken(savedMember);
                    return new OAuthTokenResponse(token,  Platform.KAKAO, platformId, savedMember.getId());
                });
    }

}