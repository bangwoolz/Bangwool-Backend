package bangwool.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoTokenRequest {
    String grant_type;
    String client_id;
    String redirect_uri;
    String client_secret;
    String code;
}
