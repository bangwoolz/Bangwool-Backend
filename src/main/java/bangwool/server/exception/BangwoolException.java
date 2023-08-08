package bangwool.server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BangwoolException extends RuntimeException{
<<<<<<< HEAD

=======
>>>>>>> 8b4bbef620e4fbc16c332e779782f67ae7209fce
    private final HttpStatus httpStatus;
    private final String message;
    private final int code;

    public BangwoolException(HttpStatus httpStatus, String message, int code) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }
}
