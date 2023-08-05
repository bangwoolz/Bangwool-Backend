package bangwool.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class WorksMonthResponse {

    private List<WorkMonthResponse> works;

    public WorksMonthResponse() {
        works = new ArrayList<>();
    }

    public void addWorkMonth(WorkMonthResponse workMonthResponse) {
        works.add(workMonthResponse);
    }
}
