package bangwool.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLoginResponse {

    private Long userId;
    private String jwt;
}
