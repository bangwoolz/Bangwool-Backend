package bangwool.server.exception.unauthorized;

public class InvalidTokenException extends UnauthorizedException{
    public InvalidTokenException() {
        super("유효하지 않은 토큰입니다.", 3004);
    }
}
