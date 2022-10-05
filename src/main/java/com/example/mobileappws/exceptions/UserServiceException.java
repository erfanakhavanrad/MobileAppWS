package com.example.mobileappws.exceptions;

// How to make SerialVersionUID
//use hotkey Ctrl+Shift+A (find action), type Serializable class without 'serialVersionUID' - the first is the one.

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 9156342452704781180L;

    public UserServiceException(String message) {
        super(message);
    }
}
