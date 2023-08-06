package bangwool.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class RankingResponses {
    private List<RankingResponse> rankingResponses;
    public RankingResponses() {
        rankingResponses = new ArrayList();
    }
}
