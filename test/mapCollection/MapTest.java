package mapCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    private Map myMap;

    public MapTest() {
    }

    @BeforeEach
    public void Setup() {
        myMap = new Map();
    }

    @Test
    public void mapIsEmptyTest() {
        assertTrue(myMap.isEmpty());
    }

    @Test
    public void mapIsNotEmptyAfterAddTest() {
        myMap.put("name", "Tyger");
        assertFalse(myMap.isEmpty());
    }

    @Test
    public void mapIsEmptyAfterRemoveTest() {
        myMap.put("name", "Tyger");
        myMap.remove("name");
        assertTrue(myMap.isEmpty());
    }

    @Test
    public void containsKeyTest() {
        myMap.put("city", "Lagos");
        assertTrue(myMap.containsKey("city"));
        assertFalse(myMap.containsKey("country"));
    }

    @Test
    public void getValueByKeyTest() {
        myMap.put("language", "Java");
        assertEquals("Java", myMap.get("language"));
    }

    @Test
    public void sizeTest() {
        assertEquals(0, myMap.size());
        myMap.put("age", "20");
        myMap.put("sex", "Male");
        assertEquals(2, myMap.size());
        myMap.remove("age");
        assertEquals(1, myMap.size());
    }

    @Test
    public void putOverwritesDuplicateKeyTest() {
        myMap.put("score", "10");
        myMap.put("score", "99");
        assertEquals("99", myMap.get("score"));
        assertEquals(1, myMap.size());
    }
}
