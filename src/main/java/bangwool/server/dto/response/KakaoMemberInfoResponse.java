package bangwool.server.dto.response;
import bangwool.server.domain.Platform;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//프로필 사진, 닉네임, 이메일
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class KakaoMemberInfoResponse {

    private Platform platform = Platform.KAKAO;
    private String token;
    private Long platformId;
    private String profileImage;
    private String nickname;
    private String email;

    public KakaoMemberInfoResponse(Long platformId, String token, String profileImage, String nickname, String email) {
        this.platformId = platformId;
        this.token = token;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.email = email;
    }
}
