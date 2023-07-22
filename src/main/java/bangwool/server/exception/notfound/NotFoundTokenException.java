package bangwool.server.exception.notfound;

public class NotFoundTokenException extends NotFoundException{
    public NotFoundTokenException() {
        super("토큰이 HTTP Header에 없습니다.", 2003);
    }
}
