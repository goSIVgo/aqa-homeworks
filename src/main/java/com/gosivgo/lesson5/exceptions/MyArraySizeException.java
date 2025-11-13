package com.gosivgo.lesson5.exceptions;

public class MyArraySizeException extends IllegalArgumentException{
    public MyArraySizeException(String message) {
        super(message);
    }
}
