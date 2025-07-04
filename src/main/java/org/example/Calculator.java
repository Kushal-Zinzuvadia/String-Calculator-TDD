package org.example;

public class Calculator {
    public int add(String input) {
        if (input == null || input.isEmpty()){
            return 0;
        } else if(input.contains(",")){
            String[] split = input.split(",");
            return Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
        } else {
            return Integer.parseInt(input);
        }
    }
}
