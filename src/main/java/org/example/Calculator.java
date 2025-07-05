package org.example;

import java.util.regex.Pattern; // Used for quoting regex special characters
import java.util.ArrayList;   // Used to collect negative numbers
import java.util.List;        // Interface for ArrayList

/**
 * The Calculator class provides functionality to add numbers from a string input.
 * It supports various delimiters and handles negative numbers by throwing an exception.
 */
public class Calculator {

    private static int callCount = 0;

    public static int getCalledCount() {
        return callCount;
    }

    public static void resetCallCount() {
        callCount = 0;
    }

    /**
     * A helper class to encapsulate the parsed delimiter regex and the string containing numbers.
     */
    private static class ParsedInput {
        final String delimiterRegex; // The regular expression to use for splitting numbers
        final String numbersString;  // The part of the input string containing only numbers

        ParsedInput(String delimiterRegex, String numbersString) {
            this.delimiterRegex = delimiterRegex;
            this.numbersString = numbersString;
        }
    }

    /**
     * Parses the input string to identify the delimiter and separate the numbers string.
     * It supports default delimiters (comma or newline) and custom delimiters.
     * Custom delimiters are defined at the start of the input in the format "//[delimiter]\n[numbers...]".
     *
     * @param rawInput The original string provided to the add method.
     * @return A {@link ParsedInput} object containing the determined delimiter regex and the numbers string.
     */
    private ParsedInput parseInputForDelimiterAndNumbers(String rawInput) {
        String currentDelimiterRegex = ",|\n"; // Default: comma or newline
        String numbersContent = rawInput;

        // Check if the input starts with a custom delimiter definition
        if (rawInput.startsWith("//")) {
            int newlineIndex = rawInput.indexOf('\n');
            if (newlineIndex != -1) {
                String delimiterSection = rawInput.substring(2, newlineIndex); // e.g., "[***]" or "[*][%]"
                numbersContent = rawInput.substring(newlineIndex + 1); // rest after \n

                List<String> delimiters = new ArrayList<>();

                // Match all [delim] parts using regex
                if (delimiterSection.startsWith("[")) {
                    // Multiple or long delimiters: //[***][%%]
                    java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\[(.*?)]").matcher(delimiterSection);
                    while (matcher.find()) {
                        delimiters.add(Pattern.quote(matcher.group(1)));
                    }
                    currentDelimiterRegex = String.join("|", delimiters);
                } else {
                    // Single-char delimiter: //;
                    currentDelimiterRegex = Pattern.quote(delimiterSection);
                }
            }
        }
        return new ParsedInput(currentDelimiterRegex, numbersContent);
    }

    /**
     * Adds numbers from a given string.
     *
     * This method supports:
     * - Empty or null strings (returns 0).
     * - Numbers separated by commas (`,`) or newlines (`\n`).
     * - Custom single-character delimiters specified at the beginning of the string
     * in the format "//[delimiter]\n[numbers...]".
     * - Throws an {@link IllegalArgumentException} if any negative numbers are found,
     * listing all negative numbers in the exception message.
     *
     * @param inputString The string containing numbers to be summed.
     * @return The sum of the numbers.
     * @throws NumberFormatException if any part of the input string cannot be converted to an integer.
     * @throws IllegalArgumentException if the input string contains one or more negative numbers.
     */
    public int add(String inputString) {
        callCount++;

        // Handle null or empty input: return 0 as there are no numbers to add.
        if (inputString == null || inputString.isEmpty()){
            return 0;
        }

        // Parse the input to get the correct delimiter regex and the string containing numbers.
        ParsedInput parsedData = parseInputForDelimiterAndNumbers(inputString);

        // Split the numbers string into individual number strings using the determined delimiter.
        String[] numberStrings = parsedData.numbersString.split(parsedData.delimiterRegex);

        int totalSum = 0; // Accumulator for the sum of numbers
        List<Integer> foundNegativeNumbers = new ArrayList<>(); // List to collect any negative numbers

        // Iterate through each part, parse it, and accumulate the sum.
        // Also, identify and collect any negative numbers.
        for (String numStr : numberStrings) {
            String trimmedNumStr = numStr.trim(); // Remove leading/trailing whitespace

            // Only process non-empty strings resulting from splitting (e.g., from "1,,2")
            if (!trimmedNumStr.isEmpty()) {
                int number = Integer.parseInt(trimmedNumStr); // Convert string to integer

                if (number < 0) {
                    foundNegativeNumbers.add(number); // Add negative number to the list
                }

                // Only add if number ≤ 1000
                if (number <= 1000) {
                    totalSum += number;
                }
            }
        }

        // If any negative numbers were found, throw an IllegalArgumentException.
        if (!foundNegativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + foundNegativeNumbers.toString());
        }

        // Return the final calculated sum.
        return totalSum;
    }
}