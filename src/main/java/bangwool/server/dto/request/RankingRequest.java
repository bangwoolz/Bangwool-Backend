package bangwool.server.dto.request;

import bangwool.server.exception.RegexException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RankingRequest {
    @NotNull(message = RegexException.NULL_EXCEPTION)
    @Positive(message = RegexException.RANKING_EXCEPTION)
    private int start;

    @NotNull(message = RegexException.NULL_EXCEPTION)
    @Positive(message = RegexException.RANKING_EXCEPTION)
    private int end;
}
