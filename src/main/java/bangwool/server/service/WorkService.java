package bangwool.server.service;

import bangwool.server.domain.Member;
import bangwool.server.domain.Ppomodoro;
import bangwool.server.domain.Work;
import bangwool.server.dto.request.WorkRequest;
import bangwool.server.dto.response.WorkResponse;
import bangwool.server.dto.response.WorkTodayResponse;
import bangwool.server.dto.response.WorksTodayResponse;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.exception.notfound.NotFoundPpomodororException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.PpomodoroRepository;
import bangwool.server.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    private final WorkRepository workRepository;
    private final PpomodoroRepository ppomodoroRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public WorkResponse save(Long memberId, Long ppomodoroId, WorkRequest request) {
        memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);
        Ppomodoro ppomodoro = ppomodoroRepository.findById(ppomodoroId)
                .orElseThrow(NotFoundPpomodororException::new);
        Work work = workRepository.save(Work.builder()
                .workedHour(request.getWorkedHour())
                .workedMin(request.getWorkedMin())
                .ppomodoro(ppomodoro)
                .build());


        return new WorkResponse(work.getId());
    }

    //todo r : 오늘의 뽀모도로 어떻게 했는지? -> 통계자료 만들어서, 랭킹

    public WorksTodayResponse findTodayPpomodoro(Long memberId) {
       memberRepository.findById(memberId)
               .orElseThrow(NotFoundMemberException::new);
        List<WorkTodayResponse> todayWorkedPpomodoro = workRepository
                .findTodayWorkByMemberId(memberId, Date.valueOf(LocalDate.now()));
        return new WorksTodayResponse(todayWorkedPpomodoro);
    }
}
