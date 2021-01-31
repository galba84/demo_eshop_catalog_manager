package com.example.ishop.messages;

import lombok.Data;

@Data
public class InfoMessage {
    String code;
    String message;

    public InfoMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
