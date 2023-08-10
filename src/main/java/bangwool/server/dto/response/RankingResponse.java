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
    private boolean isLoginedUser;

    public RankingResponse(String nickname, int workedMinute, Long memberId, Long rankingId) {
        this.nickname = nickname;
        this.workedMinute = workedMinute;
        isLoginedUser = memberId.equals(rankingId);
    }

}
