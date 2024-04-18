package com.flow.fileextensionblocker.exception;

import lombok.*;
import org.springframework.http.*;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 잘못된 요청 - 400 Bad Request */
    LENGTH_MISMATCH_BAD_REQUEST(BAD_REQUEST, "커스텀 확장자의 최대 길이는 20자까지 입니다. 확인 후 다시 시도해주시기 바랍니다."),
    NOT_LEFT_SPACE_TO_SAVE_EXTENSION_BAD_REQUEST(BAD_REQUEST, "커스텀 확장자의 최대 개수는 200개까지 입니다. 확인 후 다시 시도해주시기 바랍니다."),
    IS_NOT_ALLOWED_EXTENSION(BAD_REQUEST, "업로드가 허용되지 않는 파일 확장자 입니다. 확인 후 다시 시도해주시기 바랍니다."),
    IS_NOT_PRESENT_EXTENSION_BAD_REQUEST(BAD_REQUEST, "존재하지 않는 확장자 입니다. 확인 후 다시 시도해주시기 바랍니다."),

    /* 중복된 무언가가 존재 - 409 CONFLICT */
    IS_DUPLICATE_BAD_REQUEST(CONFLICT, "등록하려는 확장자는 커스텀 확장자 또는 기본 확장자에 이미 저장되어 있습니다. 확인 후 다시 시도해주시기 바랍니다."),

    /* 서버 에러 발생 - 500 Internal Server Error */
    FILE_SAVE_ERROR_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "파일 저장 중 에러가 발생했습니다. 확인 후 다시 시도해주시기 바랍니다."),
    PERSONAL_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "처리 중 서버 에러가 발생했습니다. 확인 후 다시 시도해주시기 바랍니다.");




    private HttpStatus httpStatus;
    private String message;
}
