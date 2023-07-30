package bangwool.server.repository;

import bangwool.server.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query("select w from Work w join fetch Ppomodoro p " +
            "where p.memberId = :memberId and w.create_date >= :today"
            + "group by p.id")
    List<Work> findTodayWorkByMemberId(Long memberId, Date today);

}