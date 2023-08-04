package bangwool.server.service;

import bangwool.server.domain.Member;
import bangwool.server.domain.Platform;
import bangwool.server.dto.request.AuthLoginRequest;
import bangwool.server.dto.request.KakaoLoginRequest;
import bangwool.server.dto.response.KakaoMemberInfoResponse;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.dto.response.OAuthTokenResponse;
import bangwool.server.dto.response.TokenResponse;
import bangwool.server.exception.badreqeust.DuplicateNickNameException;
import bangwool.server.exception.badreqeust.PasswordMismatchException;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.PlatformMemberRepository;
import bangwool.server.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final PlatformMemberRepository platformMemberRepository;

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

    public String getKakaoToken(KakaoLoginRequest request) {
        //카카오에 보낼 api
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //카카오 서버에 요청 보내기 & 응답 받기
        JSONObject response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("oauth/token")
                        .queryParam("grantType", "authorizationCode")
                        .queryParam("clientId", "${kakao.client-id}")
                        .queryParam("redirect_uri", "{kakao.redirect-uri}")
                        .queryParam("code", request.getCode())
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();

        return (String) response.get("token");
    }


    public KakaoMemberInfoResponse getKakaoMemberInfo(String token) {
        //카카오에 요청 보내기 및 응답 받기
        WebClient webClient = WebClient.builder()
                .baseUrl("http://kapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        // 사용자 정보 가져오기 : 이메일, 프로필 사진, 닉네임\
        JSONObject response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/v2/user/me").build())
                .header("Authorization", "Bearer " + token)
                .retrieve().bodyToMono(JSONObject.class).block();

        //받은 응답에서 원하는 정보 추출하기 (이메일, 닉네임, 프로필사진)
        //Long id = (Long) response.get("id");      // 여기의 id는 카카오의 userId아닌가??
        Map<String, Object> map = (Map<String, Object>) response.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) map.get("properties");
        Long id = (Long) response.get("id");
        String nickname = (String) profile.get("nickname");
        String email = (String) map.get("email");
        String profileImage = (String) profile.get("profile_image");

        return new KakaoMemberInfoResponse(id, token, profileImage, nickname, email);
    }

    /**
     * 기존 회원인지 신규회원인지 platFormId로 확인
     */
    public Optional<Long> findPlatformMember(KakaoMemberInfoResponse memberInfo) {
        Platform platform = memberInfo.getPlatform();
        Long platformId = memberInfo.getPlatformId();
        Optional<Member> platformMember = Optional.ofNullable(platformMemberRepository.findByPlatformId(memberInfo));
        return Optional.ofNullable(platformMember.get().getId());

    }

    public OAuthTokenResponse kakaoLogin(KakaoMemberInfoResponse memberInfo) {
        String email = memberInfo.getEmail();
        Long platformId = memberInfo.getPlatformId();

        if(findPlatformMember(memberInfo).isEmpty()){
            MemberSignUpResponse signUpResponse = kakaoSignUp(memberInfo);
            String token = jwtTokenProvider.createToken(signUpResponse.getId());
            return new OAuthTokenResponse(token,  Platform.KAKAO, platformId, signUpResponse.getId());
        } else{
            Member findMember = platformMemberRepository.findByPlatformId(memberInfo);
            String token = issueToken(findMember);
            return new OAuthTokenResponse(token,  Platform.KAKAO, platformId, findMember.getId());
        }
    }

    @Transactional
    public MemberSignUpResponse kakaoSignUp(KakaoMemberInfoResponse memberInfo){
        final String PARSER = "VALUES";

        Member member = Member.builder()
                .email(memberInfo.getEmail())
                .nickname(memberInfo.getNickname())
                .profile(memberInfo.getProfileImage())
                .build();

        try {
            memberRepository.save(member);
        } catch(DataIntegrityViolationException ex) {
            String errorMessage = ex.getMessage().split(PARSER)[0];
            if (errorMessage.contains("NICKNAME")) {
                throw new DuplicateNickNameException();
            }
        }
        return new MemberSignUpResponse(member.getId());
    }
}