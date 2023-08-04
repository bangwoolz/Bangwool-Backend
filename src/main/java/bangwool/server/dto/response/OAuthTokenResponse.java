package bangwool.server.dto.response;

import bangwool.server.domain.Platform;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class OAuthTokenResponse {
    private String token;
    private Platform platform;
    private Long platformId;
    private Long id;

}
