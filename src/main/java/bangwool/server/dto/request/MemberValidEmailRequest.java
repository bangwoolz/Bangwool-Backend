package bangwool.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberValidEmailRequest {
    private String email;
}
