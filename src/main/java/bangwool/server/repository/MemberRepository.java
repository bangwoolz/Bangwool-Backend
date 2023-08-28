package bangwool.server.repository;

import bangwool.server.domain.Member;
import bangwool.server.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmailAndNickname(String email, String nickname);
    Optional<Member> findByEmail(String email);

    @Query("select m.id from Member m where m.platform = :platform and m.platformId = :platformId")
    Optional<Long> findIdByPlatformAndPlatformId(Platform platform, Long platformId);

    Optional<Member> findById(Long id);
    Optional<Member> findByNickname(String nickname);

    @Modifying
    @Query("update Member m set m.password = :encodedPassword where m.id = :id")
    void updatePasswordById(Long id, String encodedPassword);
}
