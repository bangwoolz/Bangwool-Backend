package bangwool.server.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class PpomodorosResponse {
    private List<PpomodoroFindResponse> ppomodoros;
}
