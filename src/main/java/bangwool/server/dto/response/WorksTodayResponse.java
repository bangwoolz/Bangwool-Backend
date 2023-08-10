package bangwool.server.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class WorksTodayResponse {
    private List<WorkTodayResponse> works;

    public WorksTodayResponse() {
        works = new ArrayList<>();
    }
    public void addPpomo(WorkTodayResponse workTodayResponse) {
        works.add(workTodayResponse);
    }
}
