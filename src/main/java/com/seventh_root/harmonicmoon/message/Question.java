package com.seventh_root.harmonicmoon.message;

public class Question extends Message {

    private String[] responses;

    public Question(String text, String... responses) {
        super(text);
        this.responses = responses;
    }

    public String[] getResponses() {
        return responses;
    }

    public void setResponses(String[] responses) {
        this.responses = responses;
    }

}
