package bangwool.server.exception.notfound;

public class NotFoundPasswordException extends NotFoundException{
    public NotFoundPasswordException() {
        super("비밀번호가 일치하지 않습니다.", 2002);
    }
}
