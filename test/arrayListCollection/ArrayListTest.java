package arrayListCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
    private ArrayList myArrayList;

    public ArrayListTest() {
    }

    @BeforeEach
    public void Setup() {
        myArrayList = new ArrayList(10);
    }

    @Test
    public void arrayListIsEmptyTest() {
        assertTrue(myArrayList.isEmpty());
    }

    @Test
    public void addElementToArray_arrayListIsNotEmptyTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(2);
        assertFalse(myArrayList.isEmpty());
    }

    @Test
    public void addElement_removeElement_arrayListIsEmptyTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(5);
        myArrayList.remove(5);
        assertTrue(myArrayList.isEmpty());
    }

    @Test
    public void addElementsAboveTheArraySize_arrayExpandsWhenItIsFullTest() {
        assertTrue(myArrayList.isEmpty());

        for(int count = 0; count < 30; ++count) {
            myArrayList.add(15);
        }

        assertEquals(30, myArrayList.length());
    }

    @Test
    public void addMultipleElementsToArray_ArrayReturnsTheNumberOfElementsTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(10);
        myArrayList.add(20);
        myArrayList.add(30);
        assertEquals(3, myArrayList.length());
    }

    @Test
    public void addTwoElements_returnTwoElementsByIndexTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(10);
        myArrayList.add(20);
        assertEquals(10, myArrayList.returnElementInSpecifiedIndex(0));
        assertEquals(20, myArrayList.returnElementInSpecifiedIndex(1));
    }

    @Test
    public void getElementAtASpecifiedPositionTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(10);
        myArrayList.add(15);
        myArrayList.add(20);
        assertEquals(15, myArrayList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.get(10));
    }

    @Test
    public void returnAllElementsInArrayTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(10);
        myArrayList.add(15);
        myArrayList.add(20);
        myArrayList.add(25);
        myArrayList.add(30);
        myArrayList.add(35);
        myArrayList.add(40);
        myArrayList.add(45);
        myArrayList.add(50);
        myArrayList.add(55);
        int[] expected = new int[]{10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        int[] actual = myArrayList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void returnTheNumberOfElementsInArrayTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(10);
        myArrayList.add(15);
        myArrayList.add(20);
        myArrayList.add(25);
        myArrayList.add(30);
        myArrayList.add(35);
        myArrayList.add(40);
        myArrayList.add(45);
        myArrayList.add(50);
        myArrayList.add(55);
        assertEquals(10, myArrayList.size());
    }

    @Test
    public void arrayContainsSpecificElementTest() {
        assertTrue(myArrayList.isEmpty());
        myArrayList.add(10);
        myArrayList.add(15);
        myArrayList.add(20);
        myArrayList.add(25);
        myArrayList.add(30);
        myArrayList.add(35);
        myArrayList.add(40);
        myArrayList.add(45);
        myArrayList.add(50);
        myArrayList.add(55);
        assertTrue(myArrayList.contains(25));
    }
}
