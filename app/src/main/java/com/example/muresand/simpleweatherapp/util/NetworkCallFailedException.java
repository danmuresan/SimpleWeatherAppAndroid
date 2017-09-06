package com.example.muresand.simpleweatherapp.util;

/**
 * Created by muresand on 9/5/2017.
 */

public class NetworkCallFailedException extends Exception {

    private String mMessage;
    private int mHttpErrorCode;

    public NetworkCallFailedException(String message, int httpErrorCode) {
        super(message + " " + "HTTP CODE: " + httpErrorCode);
        mMessage = message;
        mHttpErrorCode = httpErrorCode;
    }

}
