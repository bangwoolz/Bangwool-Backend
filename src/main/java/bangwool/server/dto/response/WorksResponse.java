package bangwool.server.dto.response;


import bangwool.server.domain.Work;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import  java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorksResponse {
    private List<WorkResponse> works;
}
