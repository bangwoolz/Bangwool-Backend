package bangwool.server.exception.unauthorized;

public class ExpiredTokenException extends UnauthorizedException{
    public ExpiredTokenException() {
        super("만료된 토큰입니다. 다시 로그인해 주세요.", 3005);
    }
}
