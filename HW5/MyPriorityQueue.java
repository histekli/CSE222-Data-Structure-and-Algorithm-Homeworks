/**
 * A custom priority queue implementation using a MinHeap.
 * 
 * @param <T> The type of elements stored in the queue. Must be comparable.
 */
interface MyPriorityQueue<T extends Comparable<T>> {
    /**
     * Adds an element to the priority queue.
     * 
     * @param t The element to add.
     */
    void add(T t);

    /**
     * Removes and returns the highest-priority element from the queue.
     * 
     * @return The highest-priority element.
     */
    T poll();

    /**
     * Checks if the priority queue is empty.
     * 
     * @return True if the queue is empty, false otherwise.
     */
    Boolean isEmpty();
}