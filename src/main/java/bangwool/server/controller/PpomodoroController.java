package bangwool.server.controller;

import bangwool.server.dto.request.PpomodoroRequest;
import bangwool.server.dto.response.PpomodoroResponse;
import bangwool.server.dto.response.PpomodorosResponse;
import bangwool.server.security.auth.LoginUserId;
import bangwool.server.service.PpomodoroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ppomodoro", description = "뽀모도로")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ppomodoros")
@Slf4j
public class PpomodoroController {

    private final PpomodoroService ppomodoroService;

    @Operation(summary = "등록")
    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<PpomodoroResponse> save(
            @LoginUserId Long memberId,
            @Valid @RequestBody PpomodoroRequest ppomodoroRequest
    ) {
        PpomodoroResponse ppomodoroResponse = ppomodoroService.save(memberId, ppomodoroRequest);

        return ResponseEntity.ok(ppomodoroResponse);
    }

    @Operation(summary = "조회")
    @SecurityRequirement(name = "JWT")
    @GetMapping
    public ResponseEntity<PpomodorosResponse> findAll(
            @LoginUserId Long memberId
    ) {
        PpomodorosResponse ppomodoros = ppomodoroService.findAll(memberId);

        return ResponseEntity.ok(ppomodoros);
    }

    @Operation(summary = "수정")
    @SecurityRequirement(name = "JWT")
    @PutMapping("/{ppomodoroId}")
    public ResponseEntity<PpomodoroResponse> update(
            @LoginUserId Long memberId,
            @Valid @RequestBody PpomodoroRequest ppomodoroRequest,
            @PathVariable Long ppomodoroId
    ) {
        PpomodoroResponse ppomodoroResponse = ppomodoroService.update(ppomodoroId, ppomodoroRequest);

        return ResponseEntity.ok(ppomodoroResponse);
    }

    @Operation(summary = "삭제")
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{ppomodoroId}")
    public ResponseEntity<Void> delete(
            @LoginUserId Long memberId,
            @PathVariable Long ppomodoroId
    ) {
        ppomodoroService.delete(ppomodoroId);
        return ResponseEntity.ok().build();
    }
}
