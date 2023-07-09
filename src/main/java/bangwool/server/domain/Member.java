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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Pattern(regexp = Regex.EMAIL_REGEX , message = RegexException.EMAIL_EXCEPTION)
    @Column(name="email", unique = true)
    private String email;

    @Pattern(regexp = Regex.PASSWORD_REGEX, message = RegexException.PASSWORD_EXCEPTION)
    @Column(name="password")
    private String password;

    @Pattern(regexp = Regex.NICKNAME_REGEX, message = RegexException.NIKCNAME_EXCEPTION)
    @Column(name="nickname", unique = true)
    private String nickname;
}
