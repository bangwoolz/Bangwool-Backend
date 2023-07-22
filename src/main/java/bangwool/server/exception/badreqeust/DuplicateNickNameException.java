package bangwool.server.exception.badreqeust;

import bangwool.server.exception.RegexException;

public class DuplicateNickNameException extends BadRequestException {

    public DuplicateNickNameException() {
        super(RegexException.NICKNAME_DUPLICATE_EXCEPTION, 2004);
    }
}
