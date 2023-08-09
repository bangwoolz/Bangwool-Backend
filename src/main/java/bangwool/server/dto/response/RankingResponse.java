package bangwool.server.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class RankingResponse {

    private String nickname;
    private int workedMinute;

    public RankingResponse(String nickname, int workedMinute) {
        this.nickname = nickname;
        this.workedMinute = workedMinute;
    }
}
