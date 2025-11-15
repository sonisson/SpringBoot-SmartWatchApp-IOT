package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private boolean success=false;
    private Error error;

    @Getter
    @Setter
    public static class Error {
        private String code;
        private String message;
    }

    public ErrorResponse(String code,String message){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        this.error=error;
    }

}
