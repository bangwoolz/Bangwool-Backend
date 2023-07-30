package bangwool.server.repository;

import bangwool.server.domain.Work;
import bangwool.server.dto.response.WorkTodayResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query("select " +
            "new bangwool.server.dto.response.WorkTodayResponse (p.id, p.name, w.workedHour, w.workedMin) from Work w " +
            "join w.ppomodoro p " +
            "where p.member.id = :memberId and w.createDate >= :today")
    List<WorkTodayResponse> findTodayWorkByMemberId(Long memberId, Date today);

}