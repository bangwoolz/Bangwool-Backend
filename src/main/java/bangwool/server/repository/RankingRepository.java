package bangwool.server.repository;

import bangwool.server.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    @Query("select "
            + "r from Ranking r "
            + "order by r.weekWorkedMinute")
    List<Ranking> findAllorderByWeekWorkedMinute();

    @Query("select "
            + "r from Ranking r "
            + "order by r.dayWorkedMinute")
    List<Ranking> findAllorderByDayWorkedMinute();

}
