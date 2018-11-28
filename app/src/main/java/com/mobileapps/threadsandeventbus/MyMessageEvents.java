package com.mobileapps.threadsandeventbus;

public class MyMessageEvents {

    private String eventMessage;

    public MyMessageEvents(String eventMessage) {

        this.eventMessage = eventMessage;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }
}
