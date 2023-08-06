package bangwool.server.dto.response;


import bangwool.server.domain.Ppomodoro;
import bangwool.server.exception.RegexException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PpomodoroFindResponse {
    private Long id;

    private String name;

    private String color;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int workHour;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int workMin;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int restTime;


    public static PpomodoroFindResponse of(Ppomodoro ppomodoro) {
        return new PpomodoroFindResponse(ppomodoro.getId(), ppomodoro.getName(), ppomodoro.getColor(), ppomodoro.getWorkHour(), ppomodoro.getWorkMin(), ppomodoro.getRestTime());
    }
}
