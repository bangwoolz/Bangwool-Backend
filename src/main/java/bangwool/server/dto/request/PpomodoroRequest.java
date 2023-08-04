package bangwool.server.dto.request;


import bangwool.server.exception.RegexException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PpomodoroRequest {
    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    private String name;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @NotBlank(message = RegexException.BLANK_EXCEPTION)
    private String color;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int workHour;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int workMin;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int restTime;
}
