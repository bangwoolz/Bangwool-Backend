package bangwool.server.service;

import bangwool.server.dto.response.RankingResponse;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.RankingRepository;
import bangwool.server.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private final WorkRepository workRepository;
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;

    @Scheduled
    private void updateRanking() {
        //모든 멤버에 대하여
        //주간, 일간 시간 업데이트
    }

    public RankingResponse getWeekRanking() {
        return new RankingResponse(rankingRepository.findAllorderByWeekWorkedMinute());
    }

    public RankingResponse getDayRanking() {
        return new RankingResponse(rankingRepository.findAllorderByWeekWorkedMinute());
    }



}
