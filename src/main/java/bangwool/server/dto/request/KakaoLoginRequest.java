package bangwool.server.dto.request;

import bangwool.server.exception.RegexException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class KakaoLoginRequest {
    private String code;
}

