package com.mobileapps.threadsandeventbus;

public class AsyncEvent {
    private String message;

    public AsyncEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
