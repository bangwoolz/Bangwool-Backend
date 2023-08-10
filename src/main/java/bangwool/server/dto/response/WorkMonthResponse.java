package bangwool.server.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@ToString
public class WorkMonthResponse {
    private int month;
    private int day;
    private int workHour;
    private int workMin;

    private Timestamp createDate;

    public WorkMonthResponse(Timestamp createDate, int workHour, int workMin) {
        this.createDate = createDate;
        //0-11이기 때문에 1 더함
        this.month = createDate.getMonth() + 1;
        this.day = createDate.getDate();
        this.workHour = workHour + workMin / 60;
        this.workMin = workMin % 60;
    }

    public void addTime(int workHour, int workMin) {
        this.workMin = this.workMin + workMin;
        this.workHour = (this.workHour + workHour)  + this.workMin / 60;
        this.workMin = this.workMin % 60;
    }

}
