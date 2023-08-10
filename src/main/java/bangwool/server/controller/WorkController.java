package bangwool.server.controller;


import bangwool.server.dto.request.WorkRequest;
import bangwool.server.dto.request.YearMonthRequest;
import bangwool.server.dto.response.*;
import bangwool.server.security.auth.LoginUserId;
import bangwool.server.service.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Work", description = "작업")
@RequestMapping("/work")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @Operation(summary = "작업 완료 기록")
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{ppomodoroId}")
    public ResponseEntity<WorkResponse> createWorkingTime(
            @LoginUserId Long memberId,
            @Valid @RequestBody WorkRequest workRequest,
            @PathVariable Long ppomodoroId) {
        WorkResponse workResponse = workService.save(memberId, ppomodoroId, workRequest);
        return ResponseEntity.ok(workResponse);
    }

    @Operation (summary = "오늘 작업 반환")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/today")
    public ResponseEntity<WorksTodayResponse> todayWork(
            @LoginUserId Long memberId) {
        return ResponseEntity.ok(workService.findTodayPpomodoro(memberId));
    }

    @Operation(summary = "월간 통계 반환")
    @SecurityRequirement(name = "JWT")
    @PostMapping("/month")
    public ResponseEntity<WorksMonthResponse> monthWork(@LoginUserId Long memberId,
                                                        @RequestBody YearMonthRequest yearMonthRequest) {
        WorksMonthResponse worksMonthResponse = workService.findMonthPpomodoro(memberId,
                yearMonthRequest.getYear(),
                yearMonthRequest.getMonth());
        return ResponseEntity.ok(worksMonthResponse);
    }

    @Operation(summary = "주간 통계 반환")
    @SecurityRequirement(name = "JWT")
    @GetMapping("/week")
    public ResponseEntity<WorksWeekResponse> weekWork(@LoginUserId Long memberId) {
        return ResponseEntity.ok(workService.findWeekPpomodoro(memberId));
    }

}
