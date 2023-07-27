package bangwool.server.controller;

import bangwool.server.dto.request.AuthLoginRequest;
import bangwool.server.dto.response.TokenResponse;
import bangwool.server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AuthLoginRequest request) {
        TokenResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
