package bangwool.server.exception.unauthorized;

public class TokenMismatchException extends UnauthorizedException{
    public TokenMismatchException() {
        super("로그인 정보가 토큰 정보와 일치하지 않습니다.", 3003);
    }
}
