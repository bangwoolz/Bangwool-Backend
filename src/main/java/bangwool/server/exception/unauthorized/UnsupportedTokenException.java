package bangwool.server.exception.unauthorized;

public class UnsupportedTokenException extends UnauthorizedException{
    public UnsupportedTokenException() {
        super("지원되지 않는 토큰 형식입니다.", 3000);
    }
}
