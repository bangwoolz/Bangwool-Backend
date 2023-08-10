package bangwool.server.service;

import bangwool.server.dto.response.RankingResponses;
import bangwool.server.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private final RankingRepository rankingRepository;

    public void updateRanking() {

        int currentWeek = LocalDateTime.now().getDayOfWeek().getValue() - 1;
        int START_SEC_OF_DAY = 0;
        int START_MIN_OF_DAY = 0;
        int START_HOUR_OF_DAY = 6;
        Timestamp baseWeek = Timestamp.valueOf(LocalDateTime.now()
                .minusDays(currentWeek)
                .toLocalDate()
                .atTime(START_HOUR_OF_DAY, START_MIN_OF_DAY, START_SEC_OF_DAY));
        if(baseWeek.after(Timestamp.valueOf(LocalDateTime.now()))) {
            LocalDateTime.now()
                .minusDays(7)
                .toLocalDate().atTime(START_HOUR_OF_DAY, START_MIN_OF_DAY, START_SEC_OF_DAY);
        }

        Timestamp baseDay = Timestamp.valueOf(LocalDateTime.now()
                .toLocalDate().atTime(START_HOUR_OF_DAY, START_MIN_OF_DAY, START_SEC_OF_DAY));
        if(baseDay.after(Timestamp.valueOf(LocalDateTime.now()))) {
            baseDay = Timestamp.valueOf(LocalDateTime.now()
                    .minusDays(1)
                    .toLocalDate()
                    .atTime(START_HOUR_OF_DAY, START_MIN_OF_DAY, START_SEC_OF_DAY));
        }

        rankingRepository.UpdateDayWorkedByTime(baseDay);
        rankingRepository.UpdatedWeekWorkedByTime(baseWeek);
    }

    public RankingResponses getWeekRanking(Long memberId) {
        return new RankingResponses(rankingRepository.findRankByWeek(memberId));
    }

    public RankingResponses getDayRanking(Long memberId) {
        return new RankingResponses(rankingRepository.findRankByDay(memberId));
    }

}
