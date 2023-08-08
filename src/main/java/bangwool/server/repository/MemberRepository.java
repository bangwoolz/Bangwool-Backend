package bangwool.server.repository;

<<<<<<< HEAD
import bangwool.server.domain.Member;
import bangwool.server.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select m.id from Member m where m.platform = :platform and m.platformId = :platformId")
    Optional<Long> findIdByPlatformAndPlatformId(Platform platform, Long platformId);

    Optional<Member> findById(Long id);
    Optional<Member> findByNickname(String nickname);
=======
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class MemberRepository {
    @PersistenceContext
    private final EntityManager em;

    public Long getUserIdByEmail(String email) {
            return em.createQuery("select m.id from Member m where m.email=:email", Long.class)
                    .getSingleResult();
    }

    public String getPasswordByUserId(Long userId) {
        return em.createQuery("select m.id from Member m where m.id=:id", String.class)
                .getSingleResult();
    }
>>>>>>> 8b4bbef620e4fbc16c332e779782f67ae7209fce
}
