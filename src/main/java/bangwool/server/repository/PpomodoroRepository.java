package bangwool.server.repository;

import bangwool.server.domain.Member;
import bangwool.server.domain.Ppomodoro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PpomodoroRepository extends JpaRepository<Ppomodoro, Long> {
    List<Ppomodoro> findAllByMember(Member member);
}
