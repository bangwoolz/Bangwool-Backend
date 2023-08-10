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

    private final int START_HOUR_OF_DAY = 6;
    private final int START_MIN_OF_DAY = 0;


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
        return sumWorkByPpomo(
                new WorksTodayResponse(workRepository
                        .findTodayWorkByMemberId(memberId, Date.valueOf(LocalDate.now()))
                ));
    }


    public WorksMonthResponse findMonthPpomodoro(Long memberId,int year, int month) {
        memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        int START_DAY_OF_MONTH = 1;
        Timestamp baseMonth = Timestamp.valueOf(LocalDateTime.of(year, month,
                START_DAY_OF_MONTH, START_HOUR_OF_DAY,START_MIN_OF_DAY));
        Timestamp endMonth = Timestamp.valueOf(LocalDateTime.of(year, month+1,
                START_DAY_OF_MONTH, START_HOUR_OF_DAY, START_MIN_OF_DAY));
        return sumMonthWorkByDay(
                new WorksMonthResponse(workRepository.findMonthWorkByMemberId(memberId, baseMonth, endMonth))
        );
    }

    public WorksWeekResponse findWeekPpomodoro(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        int currentWeek = LocalDateTime.now().getDayOfWeek().getValue() - 1;

        int START_SEC_OF_DAY = 0;
        Timestamp baseWeek = Timestamp.valueOf(LocalDateTime.now()
                .minusDays(currentWeek)
                .toLocalDate()
                .atTime(START_HOUR_OF_DAY, START_MIN_OF_DAY, START_SEC_OF_DAY));

        List<WorkWeekResponse> workWeekResponses = workRepository
                .findWeekWorkByMemberId(memberId, baseWeek);

        return sumWeekWorkByDay(
                new WorksWeekResponse(workWeekResponses)
        );
    }

    private WorksTodayResponse sumWorkByPpomo(WorksTodayResponse workTodayResponses) {
        WorksTodayResponse worksTodayResponse = new WorksTodayResponse();
        WorkTodayResponse workTodayResponse = null;

        for(WorkTodayResponse w : workTodayResponses.getWorks()) {
            if(workTodayResponse == null) {
                workTodayResponse = w;
                continue;
            }
            if(!isSamePpomo(workTodayResponse.getPpomodoroId(), w.getPpomodoroId())) {
                worksTodayResponse.addPpomo(workTodayResponse);
                workTodayResponse = w;
                continue;
            }
            workTodayResponse.addTime(w.getWorkHour(), w.getWorkMin());
        }
        worksTodayResponse.addPpomo(workTodayResponse);

        return worksTodayResponse;
    }
    private WorksWeekResponse sumWeekWorkByDay(WorksWeekResponse workWeekResponses) {
        WorksWeekResponse worksWeekResponse = new WorksWeekResponse();
        WorkWeekResponse workWeekResponse = null;

        for(WorkWeekResponse w : workWeekResponses.getWorks()) {
            if(workWeekResponse == null) {
                workWeekResponse = w;
                continue;
            }
            if(isSameDay(workWeekResponse.getCreateDate(), w.getCreateDate())) {
                worksWeekResponse.addWorkMonth(workWeekResponse);
                workWeekResponse = w;
                continue;
            }
            workWeekResponse.addTime(w.getWorkHour(), w.getWorkMin());
        }
        worksWeekResponse.addWorkMonth(workWeekResponse);

        return worksWeekResponse;
    }

    private WorksMonthResponse sumMonthWorkByDay(WorksMonthResponse workMonthResponses) {
        WorksMonthResponse worksMonthResponse = new WorksMonthResponse();
        WorkMonthResponse workMonthResponse = null;

        for(WorkMonthResponse w : workMonthResponses.getWorks()) {
            if(workMonthResponse == null) {
                workMonthResponse = w;
                continue;
            }
            if(isSameDay(workMonthResponse.getCreateDate(), w.getCreateDate())) {
                worksMonthResponse.addWorkMonth(workMonthResponse);
                workMonthResponse = w;
                continue;
            }
            workMonthResponse.addTime(w.getWorkHour(), w.getWorkMin());
        }
        worksMonthResponse.addWorkMonth(workMonthResponse);

        return worksMonthResponse;
    }

    private boolean isSameDay(Timestamp baseDay, Timestamp comparedDay) {
        return (comparedDay.getDay() != baseDay.getDay()) &&
                comparedDay.getHours() >= 6;
    }

    private boolean isSamePpomo(Long baseId, Long compareId) {
        return baseId.equals(compareId);
    }


}
