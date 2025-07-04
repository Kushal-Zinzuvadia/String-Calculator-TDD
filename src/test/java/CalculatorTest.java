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
}
