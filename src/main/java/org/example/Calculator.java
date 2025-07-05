package org.example;

import java.util.regex.Pattern; // Import Pattern for quoting regex special characters

// The Calculator class provides functionality to perform arithmetic operations.
public class Calculator {

    // Helper class to encapsulate the parsed delimiter and numbers string
    private static class ParsedInput {
        String delimiterRegex;
        String numbersString;

        ParsedInput(String delimiterRegex, String numbersString) {
            this.delimiterRegex = delimiterRegex;
            this.numbersString = numbersString;
        }
    }

    /**
     * Parses the input string to determine the delimiter and extract the numbers string.
     * Supports default delimiters (comma or newline) or a single-character custom delimiter
     * specified at the beginning of the string in the format "//[delimiter]\n[numbers...]".
     *
     * @param input The raw input string to be parsed.
     * @return A ParsedInput object containing the determined delimiter regex and the numbers string.
     */
    private ParsedInput parseInputForDelimiterAndNumbers(String input) {
        String delimiterRegex = ",|\n"; // Default delimiters: comma or newline
        String numbersString = input;    // Initially, assume the whole input is numbers

        // Check for a custom delimiter specification.
        // A custom delimiter is indicated by "//" at the beginning of the string,
        // followed by a single delimiter character, and then a newline.
        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf('\n');
            // Ensure there is a newline character after the custom delimiter definition
            // and that the custom delimiter is a single character (input.substring(2, newlineIndex).length() == 1).
            if (newlineIndex != -1 && (newlineIndex - 2) == 1) { // (newlineIndex - 2) gives the length of the delimiter
                // Extract the single-character custom delimiter string, which is between "//" and "\n".
                String customDelimiter = input.substring(2, newlineIndex);
                // Escape the custom delimiter to treat it as a literal string in regex,
                // in case it contains special regex characters (e.g., *, +, ., etc.).
                delimiterRegex = Pattern.quote(customDelimiter);
                // The actual numbers start after the newline character.
                numbersString = input.substring(newlineIndex + 1);
            }
        }
        return new ParsedInput(delimiterRegex, numbersString);
    }

    /**
     * Adds numbers provided in a string format.
     * Supports numbers separated by commas, newlines, or a single-character custom delimiter.
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

        // Step 2: Extract delimiter and numbers string using the helper method.
        ParsedInput parsed = parseInputForDelimiterAndNumbers(input);
        String delimiterRegex = parsed.delimiterRegex;
        String numbersString = parsed.numbersString;

        // Step 3: Split the numbers string into individual number strings using the determined delimiter regex.
        String[] parts = numbersString.split(delimiterRegex);

        // Step 4: Initialize sum.
        int sum = 0;
        // Removed List<Integer> negativeNumbers as negative number handling is removed

        // Step 5: Iterate through each number string, parse, and sum.
        for(String part : parts){
            String trimmedPart = part.trim();
            if (!trimmedPart.isEmpty()) {
                int number = Integer.parseInt(trimmedPart);
                // Removed negative number check
                sum += number;
            }
        }

        // Step 6: Removed exception throwing for negative numbers.

        // Step 7: Return the final calculated sum.
        return sum;
    }
}