package bangwool.server.controller;

import bangwool.server.dto.request.MemberSignUpRequest;
import bangwool.server.dto.request.PpomodoroRequest;
import bangwool.server.dto.response.MemberSignUpResponse;
import bangwool.server.dto.response.PpomodoroResponse;
import bangwool.server.service.PpomodoroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ppomodoro", description = "뽀모도로")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ppomodoros")
@Slf4j
public class PpomodoroController {

    private final PpomodoroService ppomodoroService;

    @Operation(summary = "등록")
    @PostMapping
    public ResponseEntity<PpomodoroResponse> save(@Valid @RequestBody PpomodoroRequest ppomodoroRequest) {
        PpomodoroResponse ppomodoroResponse = ppomodoroService.save(ppomodoroRequest);

        return ResponseEntity.ok(ppomodoroResponse);
    }
}
