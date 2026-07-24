package arraySorting;

import java.util.Arrays;

public class ArraySort {
    public ArraySort() {
    }

    static void main(String[] args) {
        int[] array = new int[]{5, 1, 5, 7, 8};
        int[] secondArray = new int[]{13, 7, 1, 8, 2};
        System.out.println(Arrays.toString(getSortedArray(array)));
        System.out.println(Arrays.toString(getSortedArray(secondArray)));
    }

    public static int[] getSortedArray(int[] array) {
        int maxSum = array[0] + array[1];
        int[] newArray = new int[]{array[0], array[1]};

        for(int index = 1; index < array.length; ++index) {
            if (array[index] + array[index - 1] > maxSum) {
                maxSum = array[index] + array[index - 1];
                newArray[0] = array[index - 1];
                newArray[1] = array[index];
            }
        }

        return newArray;
    }
}
