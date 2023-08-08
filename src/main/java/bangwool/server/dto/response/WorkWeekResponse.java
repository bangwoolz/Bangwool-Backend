package bangwool.server.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;


@Getter
@NoArgsConstructor
@ToString
public class WorkWeekResponse {
    private int dayOfWeek;

    private int workHour;
    private int workMin;

    @JsonIgnore
    private Timestamp createDate;
    public WorkWeekResponse(Timestamp createDate, int workHour, int workMin) {
        this.createDate = createDate;
        //Mon : 1 ~ Sun : 7
        this.dayOfWeek = createDate.toLocalDateTime().getDayOfWeek().getValue();
        this.workHour = workHour + workMin / 60;
        this.workMin = workMin % 60;
    }

    public void addTime(int workHour, int workMin) {
        this.workHour = (this.workHour + workHour)  + workMin / 60;
        this.workMin = (this.workMin + workMin) % 60;
    }
}
