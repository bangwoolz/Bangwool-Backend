package bangwool.server.dto.request;


import bangwool.server.exception.RegexException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkRequest {
    @NotNull(message = RegexException.NULL_EXCEPTION)
    private String ppomodoroId;
    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int workedHour;
    @NotNull(message = RegexException.NULL_EXCEPTION)
    private int workedMin;
}
