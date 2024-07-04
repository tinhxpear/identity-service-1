package dev.tinhxpear.identity_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized error!"),
    USER_EXISTED(1001, "User existed!"),
    USERNAME_INVALID(1002, "Username must be at least 3 character!"),
    PASSWORD_INVALID(1003, "Password must be at least 8 character!"),
    KEY_INVALID(1004, "Invalid message key!"),
    USER_NOT_EXISTED(1005, "User not existed!"),
    UNAUTHENTICATED(1006, "Unauthenticated!"),
    ;
    private int code;
    private String message;
}
