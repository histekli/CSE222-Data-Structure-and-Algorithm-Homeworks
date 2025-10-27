package DSA.Sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Implements the Quick Sort algorithm with random pivot selection.
 * Optionally uses another sorter for small partitions.
 */
public class MyQuickSort extends GTUSorter {
    private static final int DEFAULT_PARTITION_LIMIT = 0;
    private GTUSorter otherSorterForSmallPartitions;
    private int partitionProcessingLimit;
    private Random randomPivotSelector;

    /**
     * Default constructor. Uses Quick Sort for all partitions.
     */
    public MyQuickSort() {
        this.otherSorterForSmallPartitions = null;
        this.partitionProcessingLimit = DEFAULT_PARTITION_LIMIT;
        this.randomPivotSelector = new Random();
    }

    /**
     * Constructor with a secondary sorter for small partitions.
     * 
     * @param otherSorter    Sorter for small partitions.
     * @param partitionLimit Size limit for small partitions.
     */
    public MyQuickSort(GTUSorter otherSorter, int partitionLimit) {
        if (otherSorter == null)
            throw new NullPointerException("Sorter cannot be null.");
        if (partitionLimit < 0)
            throw new IllegalArgumentException("Partition limit cannot be negative.");
        this.otherSorterForSmallPartitions = otherSorter;
        this.partitionProcessingLimit = partitionLimit;
        this.randomPivotSelector = new Random();
    }

    @Override
    protected <T> void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        if (start >= end)
            return;
        int size = end - start;
        if (otherSorterForSmallPartitions != null && size < partitionProcessingLimit) {
            otherSorterForSmallPartitions.sort(arr, start, end, comparator);
        } else {
            int pivotIndex = partition(arr, start, end, comparator);
            sort(arr, start, pivotIndex, comparator);
            sort(arr, pivotIndex + 1, end, comparator);
        }
    }

    /**
     * Partitions the array around a pivot.
     * 
     * @return Index of the pivot after partitioning.
     * @complexity O(n), where n is the size of the partition.
     */
    private <T> int partition(T[] arr, int start, int end, Comparator<T> comparator) {
        int pivotIndex = start + randomPivotSelector.nextInt(end - start);
        T pivotValue = arr[pivotIndex];
        swap(arr, start, pivotIndex);
        int i = start, j = end;
        while (true) {
            while (++i < end && comparator.compare(arr[i], pivotValue) < 0)
                ;
            while (--j > start && comparator.compare(arr[j], pivotValue) > 0)
                ;
            if (i >= j)
                break;
            swap(arr, i, j);
        }
        swap(arr, start, j);
        return j;
    }

    /**
     * Swaps two elements in the array.
     */
    private <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}