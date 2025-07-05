package org.example;

// The Calculator class provides functionality to perform arithmetic operations.
public class Calculator {

    /**
     * Adds numbers provided in a string format.
     * Supports numbers separated by commas or newlines, and handles empty or null input.
     *
     * @param input A string containing numbers to be added, separated by commas (',') or newlines ('\n').
     * Examples: "1,2,3", "1\n2", "5", or an empty string "".
     * @return The sum of the numbers, or 0 if the input is null or empty.
     * @throws NumberFormatException if any part of the input string cannot be converted into a valid integer.
     */
    public int add(String input) {
        // Step 1: Handle null or empty input.
        // If the input string is null or has no characters, there are no numbers to add,
        // so we return 0 as per the requirements.
        if (input == null || input.isEmpty()){
            return 0;
        }

        // Step 2: Split the input string into individual number strings.
        // We use a regular expression ",|\n" to split the string by either a comma OR a newline character.
        // This allows the calculator to handle inputs like "1,2,3" and "1\n2\n3" or even "1\n2,3".
        String[] parts = input.split(",|\n");

        // Step 3: Initialize a variable to store the sum of the numbers.
        int sum = 0;

        // Step 4: Iterate through each number string and add it to the sum.
        // For each 'part' (which is a string representation of a number) in the 'parts' array:
        for(String part : parts){
            // Convert the string 'part' into an integer using Integer.parseInt().
            // This method will throw a NumberFormatException if 'part' is not a valid integer string.
            // Add the resulting integer value to our running 'sum'.
            sum += Integer.parseInt(part);
        }

        // Step 5: Return the final calculated sum.
        return sum;
    }
}