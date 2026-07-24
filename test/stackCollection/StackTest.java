
package stackCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack Strings;

    public StackTest() {
    }

    @BeforeEach
    public void setUp() {
        Strings = new Stack();
    }

    @Test
    public void stackIsEmptyTest() {
        assertTrue(Strings.isEmpty());
    }

    @Test
    public void addElementToEmptyStack_stackIsNotEmptyTest() {
        assertTrue(Strings.isEmpty());
        Strings.push("A-String");
        assertFalse(Strings.isEmpty());
    }

    @Test
    public void pushX_popX_stackIsEmptyTest() {
        assertTrue(Strings.isEmpty());
        Strings.push("A-String");
        Strings.pop();
        assertTrue(Strings.isEmpty());
    }

    @Test
    public void push2_pop1_stackIsNotEmptyTest() {
        assertTrue(Strings.isEmpty());
        Strings.push("A-String");
        Strings.push("G-String");
        Strings.pop();
        assertFalse(Strings.isEmpty());
    }

    @Test
    public void push2_pop3_throwsExceptionTest() {
        assertTrue(Strings.isEmpty());
        Strings.push("A-String");
        Strings.push("G-String");
        Strings.pop();
        Strings.pop();
        assertThrows(IllegalArgumentException.class, () -> Strings.pop());
    }
}
