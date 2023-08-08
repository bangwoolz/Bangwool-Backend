package bangwool.server.exception.unauthorized;

import bangwool.server.exception.BangwoolException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BangwoolException {
<<<<<<< HEAD

=======
>>>>>>> 8b4bbef620e4fbc16c332e779782f67ae7209fce
    public UnauthorizedException(String message, int code) {
        super(HttpStatus.UNAUTHORIZED, message, code);
    }
}
