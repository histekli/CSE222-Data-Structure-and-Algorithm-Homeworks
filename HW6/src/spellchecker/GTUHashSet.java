// File: GTUHashSet.java
package src.spellchecker;

/**
 * Custom HashSet implementation using GTUHashMap internally.
 * 
 * @param <E> The type of elements maintained by this set.
 */
public class GTUHashSet<E> implements Iterable<E> {
    private transient GTUHashMap<E, Object> map;

    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();

    public GTUHashSet() {
        map = new GTUHashMap<>();
    }

    public GTUHashSet(int initialCapacity) {
        map = new GTUHashMap<>(initialCapacity);
    }

    /**
     * Adds an element to the set.
     * 
     * @param element The element to be added.
     * @return true if the element was added, false if it already exists.
     */
    public boolean add(E element) {
        if (map.containsKey(element)) {
            return false; // Element already present
        }
        map.put(element, PRESENT);
        return true; // Element added
    }

    /**
     * Removes an element from the set.
     * 
     * @param element The element to be removed.
     * @return true if the element was removed, false if it was not present.
     */
    public boolean remove(E element) {
        return map.remove(element) == PRESENT;
    }

    /**
     * Checks if the set contains the given element.
     * 
     * @param element The element to search for.
     * @return true if the element exists, false otherwise.
     */
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    // Returns the number of elements in the set.
    public int size() {
        return map.size();
    }

    // Checks if the set is empty.
    public boolean isEmpty() {
        return map.size() == 0;
    }

    // Clears all elements from the set.
    public void clear() {
        map = new GTUHashMap<>(); // Easiest way to clear
    }

    // Returns an iterator over the elements in the set.
    @Override
    public java.util.Iterator<E> iterator() {
        return map.getKeys().iterator(); // Delegate iteration to GTUHashMap's keys
    }
}