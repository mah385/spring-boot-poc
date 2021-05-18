package com.neosoft.poc.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ResponseMessage {
    private Object data;
    private int statusCode;
    private String statusRemarks;
    private String statusDescription;

    public ResponseMessage() {
    }

    public ResponseMessage(Object data, HttpStatus httpStatus, String statusDescription) {
        this.data = data;
        this.statusCode = httpStatus.value();
        this.statusRemarks = httpStatus.getReasonPhrase();
        this.statusDescription = statusDescription;
    }

}
