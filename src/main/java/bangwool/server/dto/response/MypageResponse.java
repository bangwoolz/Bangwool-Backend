package bangwool.server.dto.response;

import bangwool.server.domain.Ranking;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MypageResponse {

    private String email;
    private String name;
    private String nickname;
    private String profileImage;
    //private Ranking ranking;

}
