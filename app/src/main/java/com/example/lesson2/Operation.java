package com.example.lesson2;

import androidx.annotation.NonNull;

public enum Operation {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    POINT("."),
    PERCENT("%"),
    EQUALLY("=");

    private final String value;

    Operation(String value) {
    this.value = value;
    }

    @NonNull
    @Override
    public String toString(){
        return value;
    }
}

