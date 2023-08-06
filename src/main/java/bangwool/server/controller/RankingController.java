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

@Tag(name = "Ranking", description = "랭킹")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking")
@Slf4j
public class RankingController {
  
    private final RankingService rankingService;

    @Operation(summary = "일간 랭킹 조회")
    @PostMapping("/day")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<RankingResponses> getRankByDay(
            @LoginUserId Long memberId,
            @RequestBody RankingRequest rankingRequest) {

        return ResponseEntity.ok(
                rankingService.getDayRanking(rankingRequest.getStart(), rankingRequest.getEnd())
        );
    }

    @Operation(summary = "주간 랭킹 조회")
    @PostMapping("/week")
    public ResponseEntity<RankingResponses> getRankByWeek(
            @LoginUserId Long memberId,
            @RequestBody RankingRequest rankingRequest) {
        return ResponseEntity.ok(
                rankingService.getWeekRanking(rankingRequest.getStart(), rankingRequest.getEnd())
        );
    }
}
}

