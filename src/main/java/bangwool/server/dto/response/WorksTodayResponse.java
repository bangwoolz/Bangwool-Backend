package bangwool.server.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import  java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorksTodayResponse {
    private List<WorkTodayResponse> works;
}
