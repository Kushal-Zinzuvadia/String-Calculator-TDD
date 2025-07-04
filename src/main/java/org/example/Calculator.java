package org.example;

// The Calculator class provides functionality to perform arithmetic operations.
public class Calculator {

    /**
     * Adds numbers provided in a string format.
     * Supports comma-separated numbers and handles empty or null input.
     *
     * @param input A string containing numbers to be added, separated by commas.
     * Example: "1,2,3" or "10" or ""
     * @return The sum of the numbers, or 0 if the input is null or empty.
     * @throws NumberFormatException if any part of the input string is not a valid integer.
     */
    public int add(String input) {
        // If the input is null or an empty string, return 0 as there are no numbers to add.
        if (input == null || input.isEmpty()){
            return 0;
        }
        // If the input contains a comma, it indicates multiple numbers separated by commas.
        else if(input.contains(",")){
            // Split the input string by the comma delimiter to get individual number strings.
            String[] parts = input.split(",");
            int sum = 0; // Initialize sum to 0.
            // Iterate over each part (number string) obtained from the split.
            for(String part : parts){
                // Convert each number string to an integer and add it to the sum.
                sum += Integer.parseInt(part);
            }
            return sum; // Return the total sum of all numbers.
        }
        // If the input does not contain a comma, it's a single number.
        else {
            // Convert the single number string to an integer and return it.
            return Integer.parseInt(input);
        }
    }
}