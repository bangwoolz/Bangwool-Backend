package bangwool.server.dto.request;

import bangwool.server.exception.RegexException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class YearMonthRequest {

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    int year;
    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    int month;
}
