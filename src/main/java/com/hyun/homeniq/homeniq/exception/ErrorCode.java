package com.hyun.homeniq.homeniq.exception;

/**
 * 에러 코드 열거형
 */
public enum ErrorCode {

    // 인증 관련 (400~)
    INVALID_CREDENTIALS(400, "이메일 또는 비밀번호가 올바르지 않습니다."),
    EMAIL_ALREADY_EXISTS(400, "이미 존재하는 이메일입니다."),
    UNAUTHORIZED(401, "인증이 필요합니다."),
    FORBIDDEN(403, "권한이 없습니다."),

    // 사용자 관련 (404~)
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),

    // 서버 에러 (500~)
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다."),
    DATABASE_ERROR(500, "데이터베이스 오류가 발생했습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
