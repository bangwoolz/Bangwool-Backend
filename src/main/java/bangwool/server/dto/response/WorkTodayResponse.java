package bangwool.server.dto.response;

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
        this.workHour = workHour + workMin / 60;
        this.workMin = workMin % 60;
    }

    public void addTime(int workHour, int workMin) {
        this.workMin = this.workMin + workMin;
        this.workHour = (this.workHour + workHour)  + this.workMin / 60;
        this.workMin = this.workMin % 60;
    }
}
