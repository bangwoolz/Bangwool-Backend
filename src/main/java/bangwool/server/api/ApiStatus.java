package bangwool.server.api;

import lombok.Getter;

@Getter
public enum ApiStatus {

    SUCCESS(200, "Success"),
    SYSTEM_ERROR(500, "System Error"),
    NULL_ERROR(100, "Null Error"),
    DUPLICATE_NICKNAME(101, "Duplicate Nickname"),
    DUPLICATE_EMAIL(102, "Duplicate Email"),
    REGEX_ERROR(103, "Regex Error");

    private final int code;
    private final String message;


    ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
