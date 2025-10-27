package DSA.Sorting;

import java.util.Comparator;

/**
 * A generic class for sorting arrays.
 */
public abstract class GTUSorter {
    /**
     * Sort arr in ascending order.
     * 
     * @param <T>        Any type.
     * @param arr        Array to be sorted.
     * @param comparator A comparable of type T.
     */
    public <T> void sort(T[] arr, Comparator<T> comparator) {
        sort(arr, 0, arr.length, comparator);
    }

    /**
     * Sort elements in arr between start (inclusive) and end (exclusive) in
     * ascending order.
     * 
     * @param <T>        Any type.
     * @param arr        Array to be sorted.
     * @param start      First index (inclusive).
     * @param end        Last index (exclusive).
     * @param comparator A comparable of type T.
     */
    protected abstract <T> void sort(T[] arr, int start, int end, Comparator<T> comparator);
}
