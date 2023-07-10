package bangwool.server.domain;


import bangwool.server.exception.RegexException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*()])(?=\\S+$).{8,12}$";
    public static final String NICKNAME_REGEX = "^[a-zA-Z0-9가-힣]{1,5}$";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Pattern(regexp = EMAIL_REGEX , message = RegexException.EMAIL_EXCEPTION)
    @Column(name="email", unique = true)
    private String email;

    @Pattern(regexp = PASSWORD_REGEX, message = RegexException.PASSWORD_EXCEPTION)
    @Column(name="password")
    private String password;

    @Pattern(regexp = NICKNAME_REGEX, message = RegexException.NIKCNAME_EXCEPTION)
    @Column(name="nickname", unique = true)
    private String nickname;
}
