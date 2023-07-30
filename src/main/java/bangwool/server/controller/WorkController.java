package bangwool.server.controller;


import bangwool.server.dto.request.WorkRequest;
import bangwool.server.dto.response.WorkResponse;
import bangwool.server.dto.response.WorksTodayResponse;
import bangwool.server.security.auth.LoginUserId;
import bangwool.server.service.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Work", description = "작업")
@RequestMapping("/work")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @Operation(summary = "작업 완료 기록")
    @SecurityRequirement(name = "JWT")
    @PostMapping()
    public ResponseEntity<WorkResponse> createWorkingTime(
            @LoginUserId Long memberId,
            @Valid @RequestBody WorkRequest workRequest) {
        return ResponseEntity.ok(workService.save(memberId, workRequest));
    }

    @Operation (summary = "작업 사항 반환")
    @SecurityRequirement(name = "JWT")
    @GetMapping
    public ResponseEntity<WorksTodayResponse> updateTime(
            @LoginUserId Long memberId) {
        return ResponseEntity.ok(workService.findTodayPpomodoro(memberId));
    }

}
