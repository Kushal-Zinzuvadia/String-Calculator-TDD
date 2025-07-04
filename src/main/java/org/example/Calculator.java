package org.example;

public class Calculator {
    public int add(String input) {
        if (input == null || input.isEmpty()){
            return 0;
        } else {
            return Integer.parseInt(input);
        }
    }
}
