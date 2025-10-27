package DSA.Sorting;

import java.util.Comparator;

/**
 * Implements the Selection Sort algorithm.
 */
public class MySelectSort extends GTUSorter {

    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        for (int i = start; i < end - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < end; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * Swaps two elements in the array.
     */
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * @complexity O(n^2), where n is the size of the array.
     */
}