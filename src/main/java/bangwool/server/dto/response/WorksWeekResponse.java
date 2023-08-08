package bangwool.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
@ToString
public class WorksWeekResponse {

    private List<WorkWeekResponse> works;

    public WorksWeekResponse() {
        works = new ArrayList<>();
    }

    public void addWorkMonth(WorkWeekResponse workWeekResponse) {
        works.add(workWeekResponse);
    }
}