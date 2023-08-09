package bangwool.server.repository;

import bangwool.server.domain.Ranking;
import bangwool.server.dto.response.RankingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query("SELECT" +
            " new bangwool.server.dto.response.RankingResponse (DENSE_RANK() OVER (ORDER BY r.weekWorkedMinute), r.member.nickname, r.weekWorkedMinute )" +
            " FROM Ranking r")
    List<RankingResponse> findRankByWeek();

    @Query("SELECT" +
            " new bangwool.server.dto.response.RankingResponse (DENSE_RANK() OVER (ORDER BY r.dayWorkedMinute), r.member.nickname, r.dayWorkedMinute )" +
            " FROM Ranking r")
    List<RankingResponse> findRankByDay();

    @Modifying
    @Query("UPDATE Ranking r " +
            "SET r.dayWorkedMinute = ( " +
            "    SELECT COALESCE(SUM(w.workedHour * 60 + w.workedMin), 0) " +
            "    FROM Ppomodoro p " +
            "    JOIN Work w ON p.id = w.ppomodoro.id " +
            "    WHERE w.createDate >= :baseTime AND p.member.id = r.member.id " +
            ") ")
    void UpdateDayWorkedByTime(Timestamp baseTime);

    @Modifying
    @Query("UPDATE Ranking r " +
            "SET r.weekWorkedMinute = ( " +
            "    SELECT COALESCE(SUM(w.workedHour * 60 + w.workedMin), 0) " +
            "    FROM Ppomodoro p " +
            "    JOIN Work w ON p.id = w.ppomodoro.id " +
            "    WHERE w.createDate >= :baseTime AND p.member.id = r.member.id " +
            ") ")
    void UpdatedWeekWorkedByTime(Timestamp baseTime);

}
