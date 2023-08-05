package bangwool.server.dto.response;
import bangwool.server.domain.Platform;
import bangwool.server.dto.KakaoUser;
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
    private Long id;
    private String email;

    public static KakaoMemberInfoResponse of(KakaoUser kakaoUser) {
        return new KakaoMemberInfoResponse(
                Platform.KAKAO, kakaoUser.getId(), kakaoUser.getEmail()
        );
    }

}
