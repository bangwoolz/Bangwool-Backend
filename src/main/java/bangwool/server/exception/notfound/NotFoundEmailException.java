package bangwool.server.exception.notfound;

public class NotFoundEmailException extends NotFoundException{
    public NotFoundEmailException() {
        super("존재하지 않는 이메일입니다.", 2001);
    }
}
