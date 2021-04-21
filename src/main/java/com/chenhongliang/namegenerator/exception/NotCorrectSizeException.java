package com.chenhongliang.namegenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.RESET_CONTENT, reason = "字长度不对")
public class NotCorrectSizeException extends RuntimeException {
    public NotCorrectSizeException() {
    }
}
