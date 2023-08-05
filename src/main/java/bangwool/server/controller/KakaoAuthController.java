package bangwool.server.controller;

import bangwool.server.dto.request.KakaoLoginRequest;
import bangwool.server.dto.response.KakaoMemberInfoResponse;
import bangwool.server.dto.response.OAuthTokenResponse;
import bangwool.server.service.AuthService;
import bangwool.server.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "KakaoLogin", description = "인증")
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoAuthController {

    private final AuthService authService;
    private final MemberService memberService;

    @Operation(summary = "카카오 인카 코드 발급받기")
    @GetMapping("/oauth/authorize")
    public String kakaoConnect(){
        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("clinet_id="+"${kakao.client-id}");
        url.append("&redirect_uri=${kakao.redirect-uri}");
        url.append("&response_type=code");

        return "redirect:" + url;
    }

    @Operation(summary = "카카오 토큰 발급받기")
    @GetMapping("/callback")
    public ResponseEntity<OAuthTokenResponse> kakaoLogin(@RequestBody KakaoLoginRequest request){
        String kakaoToken = authService.getKakaoToken(request);
        KakaoMemberInfoResponse memberInfo = authService.getKakaoMemberInfo(kakaoToken);
        OAuthTokenResponse response = authService.kakaoLogin(memberInfo);
        return ResponseEntity.ok(response);
    }

}