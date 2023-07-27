package bangwool.server.dto.request;


import bangwool.server.exception.RegexException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PpomodoroRequest {

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    private LocalTime time;

    private int restTime;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    private String color;

    private String name;
}
