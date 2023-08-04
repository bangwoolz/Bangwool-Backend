package bangwool.server.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    public static final String STATUS_ACTIVE = "active";
    public static final String DEFAULT_PROFILE_ROOT = "./src/main/resources/picture/tomato.png";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "profile")
    @Builder.Default()
    private String profile = DEFAULT_PROFILE_ROOT;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "create_date")
    @Builder.Default
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());

    @UpdateTimestamp
    @Column(name = "update_date")
    @Builder.Default
    private Timestamp updateDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "status")
    @Builder.Default
    private String status = STATUS_ACTIVE;

    @Column(name = "platform")
    private Platform platform;

    @Column(name = "platform_id")
    private Long platformId;


}
