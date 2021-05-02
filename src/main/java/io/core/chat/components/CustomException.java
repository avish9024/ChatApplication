package io.core.chat.components;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends Exception{

    private int code;
    private String message;

    public CustomException(String message) {
        super(message);
    }
    public CustomException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
