package bangwool.server.domain;

public class Regex {

    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z0-9])(?=.*[!@#$%^&*()])(?=\\S+$).{8,12}$";
    public static final String NICKNAME_REGEX = "^[a-zA-Z0-9가-힣]{1,5}$";
}
