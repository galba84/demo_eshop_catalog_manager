package com.example.ishop.exceptions;

import lombok.Data;

@Data
public class DemoException extends Exception {
    public DemoException(String message) {
        super(message);
    }
}
