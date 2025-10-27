import java.util.ArrayList;
import java.util.List;

/**
 * A generic implementation of a MinHeap data structure.
 * 
 * @param <T> The type of elements stored in the heap. Must be comparable.
 */
class MinHeap<T extends Comparable<T>> implements MyPriorityQueue<T> {
    private List<T> heap;

    /**
     * Constructs an empty MinHeap.
     */
    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Adds an element to the heap and maintains the heap property.
     * 
     * @param t The element to add.
     */
    @Override
    public void add(T t) {
        heap.add(t);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Removes and returns the smallest element from the heap.
     * 
     * @return The smallest element in the heap, or null if the heap is empty.
     */
    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T item = heap.get(0);
        if (heap.size() > 1) {
            heap.set(0, heap.remove(heap.size() - 1));
        } else {
            heap.remove(0);
        }
        heapifyDown(0);
        return item;
    }

    /**
     * Checks if the heap is empty.
     * 
     * @return True if the heap is empty, false otherwise.
     */
    @Override
    public Boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Maintains the heap property by moving the element at the given index up.
     * 
     * @param index The index of the element to move up.
     */
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (parentIndex >= 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    /**
     * Maintains the heap property by moving the element at the given index down.
     * 
     * @param index The index of the element to move down.
     */
    private void heapifyDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    /**
     * Swaps two elements in the heap.
     * 
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}