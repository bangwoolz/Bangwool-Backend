package bangwool.server.exception.notfound;

import bangwool.server.exception.BangwoolException;
<<<<<<< HEAD
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends BangwoolException {

=======
import org.springframework.http.HttpStatus;

public class NotFoundException extends BangwoolException {
>>>>>>> 8b4bbef620e4fbc16c332e779782f67ae7209fce
    public NotFoundException(String message, int code) {
        super(HttpStatus.NOT_FOUND, message, code);
    }
}
