package bangwool.server.dto.request;


import bangwool.server.exception.RegexException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequest {

    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*()])(?=\\S+$).{8,12}$";
    public static final String NICKNAME_REGEX = "^[a-zA-Z0-9가-힣]{1,5}$";
    public static final String NAME_REGEX = "^[가-힣]{2,5}$";


    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    @Pattern(regexp = EMAIL_REGEX, message = RegexException.EMAIL_EXCEPTION)
    private String email;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    @Pattern(regexp = NAME_REGEX, message = RegexException.NAME_EXCEPTION)
    private String name;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    @Pattern(regexp = NICKNAME_REGEX, message = RegexException.NICKNAME_EXCEPTION)
    private String nickname;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    @Pattern(regexp = PASSWORD_REGEX, message = RegexException.PASSWORD_EXCEPTION)
    private String password;
}
