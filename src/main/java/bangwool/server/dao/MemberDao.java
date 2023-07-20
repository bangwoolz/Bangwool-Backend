package bangwool.server.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class MemberDao {
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
}
