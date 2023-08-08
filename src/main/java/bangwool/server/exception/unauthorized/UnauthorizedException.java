package bangwool.server.exception.unauthorized;

import bangwool.server.exception.BangwoolException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BangwoolException {
    public UnauthorizedException(String message, int code) {
        super(HttpStatus.UNAUTHORIZED, message, code);
    }
}
