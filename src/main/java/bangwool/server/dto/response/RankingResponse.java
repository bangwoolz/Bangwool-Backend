package bangwool.server.dto.response;

import bangwool.server.domain.Ranking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RankingResponse {
    private List<Ranking> rankingList;
}
