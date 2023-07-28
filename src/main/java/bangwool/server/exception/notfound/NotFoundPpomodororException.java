package bangwool.server.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundPpomodororException extends NotFoundException {

    public NotFoundPpomodororException() {
        super("존재하지 뽀모도로입니다.", 2001);
    }
}
