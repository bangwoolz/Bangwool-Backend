package bangwool.server.repository;

import bangwool.server.domain.Ranking;
import bangwool.server.dto.response.RankingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query("select "
            + "new.bangwool.server.dto.response.RankingResponse (r.ROW_NUMBER(), m.nickname, m.weekWorkedMinute) "
            + "from Ranking r join Member m on r.member_id = m.id " +
            " WHERE r.ROW_NUMBER() over(order by r.weekWorkedMinute) BETWEEN :start AND :end")
    List<RankingResponse> findRankByWeek(int start, int end);

    @Query("select "
            + "new.bangwool.server.dto.response.RankingResponse (r.ROW_NUMBER(), m.nickname, m.weekWorkedMinute) "
            + "from Ranking r join Member m on r.member_id = m.id " +
            " WHERE r.ROW_NUMBER() over(order by r.weekWorkedMinute) BETWEEN :start AND :end")
    List<RankingResponse> findRankByDay(int start, int end);

    @Query("UPDATE Ranking r " +
            "SET r.dayWorkedMinute = ( " +
            "    SELECT COALESCE(SUM(w.workedHour * 60 + w.workedMinute), 0) " +
            "    FROM Ppomodoro p " +
            "    JOIN Work w ON p.id = w.ppomodoro_id " +
            "    WHERE w.createDate >= :baseTime AND p.member_id = r.member_id " +
            ") " +
            "WHERE EXISTS ( " +
            "    SELECT 1 " +
            "    FROM Member m " +
            "    WHERE m.id = r.member_id)")
    void UpdateDayWorkedByTime(Timestamp baseTime);

    @Query("UPDATE Ranking r " +
            "SET r.weekWorkedMinute = ( " +
            "    SELECT COALESCE(SUM(w.workedHour * 60 + w.workedMinute), 0) " +
            "    FROM Ppomodoro p " +
            "    JOIN Work w ON p.id = w.ppomodoro_id " +
            "    WHERE w.createDate >= :baseTime AND p.member_id = r.member_id " +
            ") " +
            "WHERE EXISTS ( " +
            "    SELECT 1 " +
            "    FROM Member m " +
            "    WHERE m.id = r.member_id)")
    void UpdatedWeekWorkedByTime(Timestamp baseTime);
}
