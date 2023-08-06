package bangwool.server.dto.response;

import bangwool.server.domain.Ranking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RankingResponses {
    List<RankingResponse> rankingResponses;
}
