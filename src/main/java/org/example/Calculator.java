package org.example;

import java.util.regex.Pattern; // Import Pattern for quoting regex special characters

// The Calculator class provides functionality to perform arithmetic operations.
public class Calculator {

    /**
     * Adds numbers provided in a string format.
     * Supports numbers separated by commas, newlines, or a custom delimiter.
     * Custom delimiters are specified at the beginning of the string in the format "//[delimiter]\n[numbers...]".
     *
     * @param input A string containing numbers to be added.
     * Examples: "1,2,3", "1\n2", "5", "", or "//;\n1;2".
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

        String delimiterRegex = ",|\n"; // Default delimiters: comma or newline
        String numbersString = input;    // Initially, assume the whole input is numbers

        // Step 2: Check for a custom delimiter specification.
        // A custom delimiter is indicated by "//" at the beginning of the string,
        // followed by the delimiter character(s), and then a newline.
        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf('\n');
            // Ensure there is a newline character after the custom delimiter definition.
            if (newlineIndex != -1) {
                // Extract the custom delimiter string, which is between "//" and "\n".
                String customDelimiter = input.substring(2, newlineIndex);
                // Escape the custom delimiter to treat it as a literal string in regex,
                // in case it contains special regex characters (e.g., *, +, ., etc.).
                delimiterRegex = Pattern.quote(customDelimiter);
                // The actual numbers start after the newline character.
                numbersString = input.substring(newlineIndex + 1);
            }
        }

        // Step 3: Split the numbers string into individual number strings using the determined delimiter regex.
        // If the numbersString is empty after potentially extracting a custom delimiter,
        // this split might result in an array with a single empty string.
        String[] parts = numbersString.split(delimiterRegex);

        // Step 4: Initialize a variable to store the sum of the numbers.
        int sum = 0;

        // Step 5: Iterate through each number string and add it to the sum.
        // For each 'part' (which is a string representation of a number) in the 'parts' array:
        for(String part : parts){
            // Trim any leading/trailing whitespace from the part to ensure correct parsing.
            // This handles cases like " 1" or "2 ".
            String trimmedPart = part.trim();
            // If the trimmed part is empty (e.g., from consecutive delimiters or trailing delimiter), skip it.
            if (!trimmedPart.isEmpty()) {
                // Convert the string 'trimmedPart' into an integer using Integer.parseInt().
                // This method will throw a NumberFormatException if 'trimmedPart' is not a valid integer string.
                // Add the resulting integer value to our running 'sum'.
                sum += Integer.parseInt(trimmedPart);
            }
        }

        // Step 6: Return the final calculated sum.
        return sum;
    }
}