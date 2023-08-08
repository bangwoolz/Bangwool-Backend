package bangwool.server.exception.unauthorized;

<<<<<<< HEAD
public class InvalidTokenException extends UnauthorizedException {

    public InvalidTokenException() {
        super("올바르지 않은 토큰입니다. 다시 로그인해주세요.", 1015);
    }

    public InvalidTokenException(String message) {
        super(message, 1015);
=======
public class InvalidTokenException extends UnauthorizedException{
    public InvalidTokenException() {
        super("유효하지 않은 토큰입니다.", 3004);
>>>>>>> 8b4bbef620e4fbc16c332e779782f67ae7209fce
    }
}
