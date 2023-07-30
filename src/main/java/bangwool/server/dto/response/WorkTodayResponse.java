package bangwool.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class WorkTodayResponse {

    private Long ppomodoroId;
    private String name;
    private int workHour;
    private int workMin;

    public WorkTodayResponse(Long ppomodoroId, String name, int workHour, int workMin) {
        this.ppomodoroId = ppomodoroId;
        this.name = name;
        this.workHour = workHour;
        this.workMin = workMin;
    }
}
