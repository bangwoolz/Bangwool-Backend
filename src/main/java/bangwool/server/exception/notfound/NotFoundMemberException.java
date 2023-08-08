package bangwool.server.exception.notfound;

import lombok.Getter;

<<<<<<< HEAD
@Getter
public class NotFoundMemberException extends NotFoundException {

    public NotFoundMemberException() {
        super("존재하지 않는 회원입니다.", 1001);
    }
}
=======

@Getter
public class NotFoundMemberException extends NotFoundException {
    public NotFoundMemberException() {
        super("존재하지 않는 회원입니다.", 2000);
    }
}
>>>>>>> 8b4bbef620e4fbc16c332e779782f67ae7209fce
