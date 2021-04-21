package com.chenhongliang.namegenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "字未录入字库")
public class NoCharacterException extends RuntimeException {
    public NoCharacterException() {
    }
}
