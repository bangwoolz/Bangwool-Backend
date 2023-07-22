package bangwool.server.exception.unauthorized;

public class MalformedTokenException extends UnauthorizedException{
    public MalformedTokenException() {
        super("토큰이 올바르게 구성되지 않았습니다.", 3001);
    }
}
