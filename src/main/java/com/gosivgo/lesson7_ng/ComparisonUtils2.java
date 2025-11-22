package com.gosivgo.lesson7_ng;

public class ComparisonUtils2 {
    public String compare(int a, int b) {
        return String.format("%d %s %d", a, a > b ? ">" : a < b ? "<" : "=", b);
    }
}
