package com.gosivgo.lesson7.junit5;

public class ComparisonUtils {
    public String compare(int a, int b) {
        return String.format("%d %s %d", a, a > b ? ">" : a < b ? "<" : "=", b);
    }
}
