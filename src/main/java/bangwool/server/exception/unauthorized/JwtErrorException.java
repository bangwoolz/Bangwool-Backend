package bangwool.server.exception.unauthorized;

public class JwtErrorException extends UnauthorizedException{
    public JwtErrorException() {
        super("JWT에서 오류가 발생하였습니다.", 3002);
    }
}
