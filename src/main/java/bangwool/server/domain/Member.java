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
    public static final String ID = "ID";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String NICKNAME = "NICKNAME";
    public static final String NAME = "NAME";
    public static final String STATUS = "STATUS";
    public static final String PROFILE = "PROFILE";
    public static final String CREATE_DATE = "CREATE_DATE";
    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String STATUS_ACTIVE = "active";
    public static final String DEFAULT_PROFILE_ROOT = "./src/main/resources/picture/tomato.png";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;
    @Column(name = EMAIL, unique = true)
    private String email;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = NICKNAME, unique = true)
    private String nickname;

    @Column(name = PROFILE)
    @Builder.Default()
    private String profile = DEFAULT_PROFILE_ROOT;

    @Column(name = NAME)
    private String name;

    @CreationTimestamp
    @Column(name = CREATE_DATE)
    @Builder.Default
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());

    @UpdateTimestamp
    @Column(name = UPDATE_DATE)
    @Builder.Default
    private Timestamp updateDate = new Timestamp(System.currentTimeMillis());

    @Column(name = STATUS)
    @Builder.Default
    private String status = STATUS_ACTIVE;
}
