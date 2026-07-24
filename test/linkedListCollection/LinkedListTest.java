package linkedListCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList list;

    public LinkedListTest() {
    }

    @BeforeEach
    public void setUp() {
        list = new LinkedList();
    }

    @Test
    public void linkedListIsEmptyTest() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void addElement_listIsNotEmptyTest() {
        list.add(10);
        assertFalse(list.isEmpty());
    }

    @Test
    public void addElement_listSizeIncrementTest() {
        list.add(10);
        assertEquals(1, list.size());
    }

    @Test
    public void returnFirstElementTest() {
        list.add(10);
        assertEquals(10, list.get(0));
    }

    @Test
    public void returnElementsInInsertionOrderTest() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void addXY_removeXElementTest() {
        list.add(10);
        list.add(20);
        list.remove(10);
        assertEquals(1, list.size());
        assertEquals(20, list.get(0));
    }

    @Test
    public void removeLastElement_listIsEmptyTest() {
        list.add(10);
        list.remove(10);
        assertTrue(list.isEmpty());
    }

    @Test
    public void getFromEmptyList_throwExceptionTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void getIndexThatDoesNotExist_throwExceptionTest() {
        list.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void removeElementNotInList_throwExceptionTest() {
        list.add(10);
        assertThrows(IllegalArgumentException.class, () -> list.remove(20));
    }
}
