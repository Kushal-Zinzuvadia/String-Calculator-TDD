import org.example.Calculator;
import org.junit.Assert;
import org.junit.Before; // Import Before annotation
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator; // Declare Calculator instance at class level

    /**
     * Set up method to initialize the Calculator instance before each test.
     * This follows the DRY (Don't Repeat Yourself) principle.
     */
    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Before
    public void resetCallCount() {
        Calculator.resetCallCount();
    }

    @Test
    public void testEmptyStringReturnsZero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void testTwoNumbersDelimitedByComma() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    public void testMultipleNumbersDelimitedByComma() {
        assertEquals(6, calculator.add("1,2,3"));
    }

    @Test
    public void testNumbersDelimitedByCommaOrNewline(){
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testCustomDelimiter() {
        // Test with semicolon as a custom delimiter
        assertEquals(3, calculator.add("//;\n1;2"));
        // Test with a different custom delimiter, e.g., '$'
        assertEquals(10, calculator.add("//$\n4$6"));
        // Test with a custom delimiter and multiple numbers
        assertEquals(15, calculator.add("//*\n1*2*3*4*5"));
    }

    @Test
    public void testNegativeNumberThrowsException() {
        // Expect an IllegalArgumentException when a single negative number is passed
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3"); // Input with a negative number
        });
        // Verify the exception message contains "negatives not allowed" and the specific negative number
        assertTrue(exception.getMessage().contains("negatives not allowed"));
        assertTrue(exception.getMessage().contains("-2"));
    }

    @Test
    public void testMultipleNegativeNumbersThrowsException() {
        // Expect an IllegalArgumentException when multiple negative numbers are passed
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("-1,2,-3\n-4"); // Input with multiple negative numbers
        });
        assertTrue(exception.getMessage().contains("negatives not allowed"));
        // Verify that all negative numbers are listed in the exception message
        assertTrue(exception.getMessage().contains("-1"));
        assertTrue(exception.getMessage().contains("-3"));
        assertTrue(exception.getMessage().contains("-4"));

        // A more robust way to check if the exact list of negatives is present
        // This assumes the toString() format of ArrayList is consistent.
        String expectedMessagePart = "[-1, -3, -4]";
        assertTrue(exception.getMessage().contains(expectedMessagePart));
    }

    @Test
    public void testGetCalledCount() {
        // This line will cause a compilation error because Calculator.getCalledCount() does not exist.
        // The error would typically be "cannot find symbol" or "method getCalledCount() not found".
        assertEquals(0, Calculator.getCalledCount());
    }

    @Test
    public void testGetCalledCountAfterAddCalls() {
        Calculator calculator = new Calculator();
        calculator.add("1,2");
        calculator.add("3");
        assertEquals(2, calculator.getCalledCount());
    }

    @Test
    public void testIgnoreNumbersGreaterThan1000() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.add("2,1001"));
    }

    @Test
    public void testCustomDelimiterAnyLength() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void testMultipleDelimiters() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }
}
