package bangwool.server.service;

import bangwool.server.dto.response.RankingResponses;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.RankingRepository;
import bangwool.server.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private final WorkRepository workRepository;
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;

    private final int START_HOUR_OF_DAY = 6;
    private final int START_MIN_OF_DAY = 0;
    private final int START_SEC_OF_DAY = 0;

    @Scheduled(cron = "0 0 * * * *")
    protected void updateRanking() {

        int currentWeek = LocalDateTime.now().getDayOfWeek().getValue() - 1;

        Timestamp baseWeek = Timestamp.valueOf(LocalDateTime.now()
                .minusDays(currentWeek)
                .toLocalDate()
                .atTime(START_HOUR_OF_DAY, START_MIN_OF_DAY, START_SEC_OF_DAY));

        Timestamp baseDay = Timestamp.valueOf(LocalDateTime.now()
                .toLocalDate().atTime(START_HOUR_OF_DAY, START_MIN_OF_DAY, START_SEC_OF_DAY));

        rankingRepository.UpdateDayWorkedByTime(baseDay);
        rankingRepository.UpdatedWeekWorkedByTime(baseWeek);
    }

    public RankingResponses getWeekRanking(int start, int end) {
        return new RankingResponses(rankingRepository.findRankByWeek(start, end));
    }

    public RankingResponses getDayRanking(int start, int end) {
        return new RankingResponses(rankingRepository.findRankByDay(start, end));
    }
}
