package com.gosivgo.lesson5.exceptions;

public class MyArrayDataException extends NumberFormatException {
    private final int row;
    private final int col;

    public MyArrayDataException(String message, int row, int col) {
        super(message);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
