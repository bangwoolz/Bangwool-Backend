package bangwool.server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class KakaoTokenResponse {
    String access_token;
    String token_type;
    String refresh_token;
    Integer expires_in;
    String scope;
    Integer refresh_token_expires_in;

}
