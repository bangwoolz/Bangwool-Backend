package bangwool.server.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundPpomodoroException extends NotFoundException {

    public NotFoundPpomodoroException() {
        super("존재하지 뽀모도로입니다.", 2001);
    }
}
