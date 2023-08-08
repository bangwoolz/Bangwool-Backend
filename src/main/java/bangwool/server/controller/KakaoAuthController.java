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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "KakaoLogin", description = "인증")
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoAuthController {

    private final AuthService authService;
    @Value("${kakao.api-key}")
    String clientId;
    @Value("${kakao.redirect-uri}")
    String redirectUri;

//    @Operation(summary = "카카오 인카 코드 발급받기")
//    @GetMapping("/oauth/authorize")
//    public String kakaoConnect(){
//        StringBuffer url = new StringBuffer();
//
//        url.append("https://kauth.kakao.com/oauth/authorize?");
//        url.append("client_id="+clientId);
//        url.append("&redirect_uri="+redirectUri);
//        url.append("&response_type=code");
//
//        return "redirect:" + url;
//    }

    @Operation(summary = "카카오 토큰 발급받기")
    @GetMapping("/callback")
    public String kakaoToken(@RequestParam String code) {
        String kakaoToken = authService.getKakaoToken(code);
        return kakaoToken;
        //KakaoMemberInfoResponse memberInfo = authService.getKakaoMemberInfo(kakaoToken);
    }

    @Operation(summary = "카카오 로그인 및 회원가입")
    @GetMapping ("/login")
    public ResponseEntity<OAuthTokenResponse> kakaoLogin(String kakaoToken){
        OAuthTokenResponse response = authService.kakaoLogin(authService.getKakaoMemberInfo(kakaoToken));
        return ResponseEntity.ok(response);
    }
}