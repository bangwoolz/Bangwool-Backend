package bangwool.server.service;

import bangwool.server.domain.Ppomodoro;
import bangwool.server.domain.Work;
import bangwool.server.dto.request.WorkRequest;
import bangwool.server.dto.response.*;
import bangwool.server.exception.notfound.NotFoundMemberException;
import bangwool.server.exception.notfound.NotFoundPpomodoroException;
import bangwool.server.repository.MemberRepository;
import bangwool.server.repository.PpomodoroRepository;
import bangwool.server.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                .orElseThrow(NotFoundPpomodoroException::new);
        Work work = workRepository.save(Work.builder()
                .workedHour(request.getWorkedHour())
                .workedMin(request.getWorkedMin())
                .ppomodoro(ppomodoro)
                .build());


        return new WorkResponse(work.getId());
    }

    public WorksTodayResponse findTodayPpomodoro(Long memberId) {
       memberRepository.findById(memberId)
               .orElseThrow(NotFoundMemberException::new);
        List<WorkTodayResponse> todayWorkedPpomodoro = workRepository
                .findTodayWorkByMemberId(memberId, Date.valueOf(LocalDate.now()));

        return new WorksTodayResponse(todayWorkedPpomodoro);
    }

    public WorksMonthResponse findMonthPpomodoro(Long memberId,int year, int month) {
        memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        Timestamp baseMonth = Timestamp.valueOf(LocalDateTime.of(year,month,1,6,0));
        Timestamp endMonth = Timestamp.valueOf(LocalDateTime.of(year, month+1, 1, 6, 0));
        return sumSameDayWork(
                new WorksMonthResponse(workRepository.findMonthWorkByMemberId(memberId, baseMonth, endMonth))
        );
    }

    private WorksMonthResponse sumSameDayWork(WorksMonthResponse workMonthResponses) {
        WorksMonthResponse worksMonthResponse = new WorksMonthResponse();
        WorkMonthResponse workMonthResponse = null;

        for(WorkMonthResponse w : workMonthResponses.getWorks()) {
            if(workMonthResponse == null) {
                workMonthResponse = w;
                continue;
            }
            if(!isSameDay(workMonthResponse, w)) {
                worksMonthResponse.addWorkMonth(workMonthResponse);
                workMonthResponse = w;
                continue;
            }
            workMonthResponse.addTime(w.getWorkHour(), w.getWorkMin());
        }
        worksMonthResponse.addWorkMonth(workMonthResponse);

        return worksMonthResponse;
    }

    private boolean isSameDay(WorkMonthResponse baseDay, WorkMonthResponse comparedDay) {
        return (comparedDay.getCreateDate().getDay() == baseDay.getCreateDate().getDay()) ||
                comparedDay.getCreateDate().getHours() < 6;
    }


}
