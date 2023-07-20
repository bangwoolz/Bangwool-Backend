package bangwool.server.controller;

import bangwool.server.dto.PostLoginRequest;
import bangwool.server.dto.PostLoginResponse;
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
    public PostLoginResponse login(@Validated PostLoginRequest loginRequest){
        log.info("[AuthController.login]");
        return authService.login(loginRequest);
    }

}
