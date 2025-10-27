package DSA.Sorting;

import java.util.Comparator;

/**
 * Implements the Insertion Sort algorithm.
 */
public class MyInsertSort extends GTUSorter {

    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        for (int i = start + 1; i < end; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= start && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    /**
     * @complexity O(n^2), where n is the size of the array.
     */
}