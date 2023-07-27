package bangwool.server.repository;

import bangwool.server.domain.Ppomodoro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PpomodoroRepository extends JpaRepository<Ppomodoro, Long> {
}
