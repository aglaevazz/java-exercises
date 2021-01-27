/*
Java 15
This program implements selection sort.
 */

import java.util.Arrays;

public class selectionSort {

    public static int[] selectionSortMethod(int[] array) {
        for (int lastIndexSorted = 0; lastIndexSorted < array.length; lastIndexSorted++) {
            int indexCurrentMinimum = lastIndexSorted;
            for (int currentIndexUnsorted = lastIndexSorted + 1; currentIndexUnsorted < array.length;
                 currentIndexUnsorted++) {
                if (array[currentIndexUnsorted] < array[indexCurrentMinimum]) {
                    indexCurrentMinimum = currentIndexUnsorted;
                }
            }
            if (lastIndexSorted != indexCurrentMinimum) {
                int current_element = array[lastIndexSorted];
                array[lastIndexSorted] = array[indexCurrentMinimum];
                array[indexCurrentMinimum] = current_element;
            }
        }
        return array;
    }

    public static void main (String[] args) {
        int[] my_array = {7, 2, 5, 1, 6};
        int[] sorted_array = selectionSortMethod(my_array);
        System.out.println(Arrays.toString(sorted_array));
    }
}
