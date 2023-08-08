package bangwool.server.controller;

<<<<<<< HEAD
import bangwool.server.dto.request.AuthLoginRequest;
import bangwool.server.dto.response.TokenResponse;
import bangwool.server.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login", description = "인증")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "자체 로그인")
    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AuthLoginRequest request) {
        TokenResponse response = authService.login(request);
        return ResponseEntity.ok(response);
=======
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
>>>>>>> 8b4bbef620e4fbc16c332e779782f67ae7209fce
    }

}
