package bangwool.server.controller;

import bangwool.server.domain.Member;
import bangwool.server.dto.request.RankingRequest;
import bangwool.server.dto.response.RankingResponses;
import bangwool.server.security.auth.LoginUserId;
import bangwool.server.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    RankingService rankingService;
    @Operation(summary = "일간 랭킹 조회")
    @GetMapping("/day")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<RankingResponses> getRankByDay(
            @LoginUserId Member member,
            @RequestBody RankingRequest rankingRequest) {

        return ResponseEntity.ok(
                rankingService.getDayRanking(rankingRequest.getStart(), rankingRequest.getEnd())
        );
    }

    @Operation(summary = "주간 랭킹 조회")
    @GetMapping("/week")
    public ResponseEntity<RankingResponses> getRankByWeek(@RequestBody RankingRequest rankingRequest) {
        return ResponseEntity.ok(
                rankingService.getWeekRanking(rankingRequest.getStart(), rankingRequest.getEnd())
        );
    }
}