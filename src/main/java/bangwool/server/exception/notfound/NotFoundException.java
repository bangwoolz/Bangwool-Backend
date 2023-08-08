package bangwool.server.exception.notfound;

import bangwool.server.exception.BangwoolException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BangwoolException {
    public NotFoundException(String message, int code) {
        super(HttpStatus.NOT_FOUND, message, code);
    }
}
