package bangwool.server.repository;

import bangwool.server.domain.Member;
import bangwool.server.domain.Platform;
import bangwool.server.dto.response.KakaoMemberInfoResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlatformMemberRepository {
    @PersistenceContext
    private EntityManager em;

    public Member findByPlatformId(KakaoMemberInfoResponse memberInfo){
        Platform platForm = memberInfo.getPlatform();
        Long platformId = memberInfo.getPlatformId();

        return em.createQuery("select m from Member m where m.platform = :platform and m.platformId = :platformId", Member.class)
                .getSingleResult();
    }
}
