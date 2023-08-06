package bangwool.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class RankingResponse {


    private int rank;
    private String nickname;
    private int workedHour;
    private int workedMin;

    public RankingResponse(int rank, String nickname, int workedMin) {
        this.rank = rank;
        this.nickname = nickname;
        this.workedMin = workedMin % 60;
        this.workedHour = workedMin / 60;
    }
}
