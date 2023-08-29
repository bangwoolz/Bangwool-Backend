package bangwool.server.repository;

import bangwool.server.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
