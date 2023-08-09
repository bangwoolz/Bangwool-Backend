package bangwool.server.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class KakaoLoginRequest {
    private String token;
}

