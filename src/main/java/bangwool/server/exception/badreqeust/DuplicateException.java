package bangwool.server.exception.badreqeust;

import bangwool.server.exception.BangwoolException;
import bangwool.server.exception.RegexException;
import org.springframework.http.HttpStatus;

public class DuplicateException extends BangwoolException {
    public DuplicateException() {
        super(HttpStatus.BAD_REQUEST, RegexException.DUPLICATE_EXCEPTION,12345);
    }
}
