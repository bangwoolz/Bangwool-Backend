package bangwool.server.service;

import bangwool.server.domain.Member;
import bangwool.server.domain.Work;
import bangwool.server.dto.request.WorkRequest;
import bangwool.server.dto.response.WorkResponse;
import bangwool.server.dto.response.WorksResponse;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkService {

    @Autowired
    private final WorkRepository workRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public WorkResponse save(Long memberId, WorkRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
        Work work = workRepository.save(Work.builder()
                .workedHour(request.getWorkedHour())
                .workedMin(request.getWorkedMin())
                .build());


        return new WorkResponse(work.getId());
    }

    //todo r : 오늘의 뽀모도로 어떻게 했는지? -> 통계자료 만들어서, 랭킹

    public WorksResponse findTodayPpomodoro(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
        List<Work> works = workRepository.findTodayWorkByMemberId(memberId, Date.valueOf(LocalDate.now()));
        List<WorkResponse> workResponses = works.stream().map(WorkResponse::from).toList();
        return new WorksResponse(workResponses);
    }

    /**
     * 통
     */
}
