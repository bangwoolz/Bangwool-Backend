package bangwool.server.domain;

public class Regex {

    static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*()](?=\\S+$).{8.12}$";
    static final String NICKNAME_REGEX = "^[A-Za-z0-9가-힣]*$.{0,10}";
}
