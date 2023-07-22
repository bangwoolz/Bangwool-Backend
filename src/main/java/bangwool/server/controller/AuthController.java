package bangwool.server.controller;

import bangwool.server.dto.MemberLoginRequest;
import bangwool.server.dto.MemberLoginResponse;
import bangwool.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 기능 구현
     */
    @PostMapping("/login")
    public MemberLoginResponse login(@Validated MemberLoginRequest loginRequest){
        log.info("[AuthController.login]");
        return authService.login(loginRequest);
    }

}
