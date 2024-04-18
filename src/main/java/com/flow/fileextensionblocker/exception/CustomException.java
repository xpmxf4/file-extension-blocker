package com.flow.fileextensionblocker.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
}
