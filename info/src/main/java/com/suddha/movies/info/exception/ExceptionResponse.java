package com.suddha.movies.info.exception;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ExceptionResponse {

    private Map<String, String> errorMessages;
    private Date errorTime = new Date();

    public ExceptionResponse(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
