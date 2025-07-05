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
}
