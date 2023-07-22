package bangwool.server.exception.badreqeust;

import bangwool.server.exception.RegexException;

public class DuplicateEmailException extends BadRequestException{

    public DuplicateEmailException() {
        super(RegexException.EMAIL_DUPLICATE_EXCEPTION, 2005);
    }
}
