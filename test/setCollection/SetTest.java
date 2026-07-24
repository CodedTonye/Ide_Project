package setCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {
    private Set mySet;

    public SetTest() {
    }

    @BeforeEach
    public void Setup() {
        mySet = new Set(5);
    }

    @Test
    public void createEmptySet_setIsEmptyTest() {

        assertTrue(mySet.isEmpty());
    }

    @Test
    public void addElementToSet_setIsNotEmptyTest() {

        assertTrue(mySet.add("Man"));

        assertFalse(mySet.isEmpty());
    }

    @Test
    public void addElementToSet_removeElementInSet_setIsEmptyTest() {

        assertTrue(mySet.add("Man"));
        mySet.remove("Man");

        assertTrue(mySet.isEmpty());
    }
}
