package bangwool.server.repository;

import bangwool.server.domain.Work;
import bangwool.server.dto.response.WorkMonthResponse;
import bangwool.server.dto.response.WorkTodayResponse;
import bangwool.server.dto.response.WorkWeekResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    @Query("select "
            + "new bangwool.server.dto.response.WorkTodayResponse (p.id, p.name, w.workedHour, w.workedMin) from Work w "
            + "join w.ppomodoro p where p.member.id = :memberId and w.createDate >= :today")
    List<WorkTodayResponse> findTodayWorkByMemberId(Long memberId, Date today);

    @Query("select "
    + "new bangwool.server.dto.response.WorkMonthResponse (w.createDate, w.workedHour, w.workedMin) "
            + "from Work w join w.ppomodoro p " +
            "where p.member.id = :memberId and w.createDate between :baseMonth and :endMonth order by w.createDate")
    List<WorkMonthResponse> findMonthWorkByMemberId(Long memberId, Timestamp baseMonth, Timestamp endMonth);

    @Query("select "
            + "new bangwool.server.dto.response.WorkWeekResponse (w.createDate, w.workedHour, w.workedMin) "
            + "from Work w join w.ppomodoro p " +
            "where p.member.id = :memberId and w.createDate >= :week")
    List<WorkWeekResponse> findWeekWorkByMemberId(Long memberId, Timestamp week);
}
