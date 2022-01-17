package com.suddha.movies.info.exception;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExceptionResponse  {

    private List<String> errorMessages;
    private Date errorDate;
}
