package arraySorting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArraySortTest {
    public ArraySortTest() {
    }

    @Test
    public void testThatNewArrayReturnsTwoElementsWithTheMaxSum() {
        int[] array = new int[]{5, 1, 5, 7, 8};
        int[] actual = ArraySort.getSortedArray(array);
        int[] expected = new int[]{7, 8};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testThatNewArrayReturnsTwoElementsWithTheMaxSumCorrectly() {
        int[] secondArray = new int[]{13, 7, 1, 8, 2};
        int[] actual = ArraySort.getSortedArray(secondArray);
        int[] expected = new int[]{13, 7};
        assertArrayEquals(expected, actual);
    }
}
