import org.example.Calculator;
import org.junit.*;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testEmptyStringReturnsZero() {
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void testTwoNumbersDelimitedByComma() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    public void testMultipleNumbersDelimitedByComma() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.add("1,2,3"));
    }

    @Test public void testNumbersDelimitedByCommaOrNewline(){
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testCustomDelimiter() {
        Calculator calculator = new Calculator();
        // Test with semicolon as a custom delimiter
        assertEquals(3, calculator.add("//;\n1;2"));

        // Test with a custom delimiter and multiple numbers
        assertEquals(15, calculator.add("//*\n1*2*3*4*5"));
    }

    @Test
    public void testNegativeNumberThrowsException() {
        Calculator calculator = new Calculator();
        // Expect an IllegalArgumentException when a negative number is passed
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3"); // Input with a negative number
        });
        // Verify the exception message contains "negatives not allowed" and the specific negative number
        assertTrue(exception.getMessage().contains("negatives not allowed"));
        assertTrue(exception.getMessage().contains("-2"));
    }

    @Test
    public void testMultipleNegativeNumbersThrowsException() {
        Calculator calculator = new Calculator();
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("-3,-5"); // Input with multiple negative numbers
        });
        assertTrue(exception.getMessage().contains("negatives not allowed"));
        // Verify that all negative numbers are listed in the exception message
        assertTrue(exception.getMessage().contains("-3"));
        assertTrue(exception.getMessage().contains("-5"));
    }
}
