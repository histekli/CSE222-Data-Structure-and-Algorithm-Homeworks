package src.spellchecker;

import java.util.Arrays;

/**
 * A custom dynamic array list implementation, as java.util.List is forbidden.
 * 
 * @param <E> The type of elements in this list.
 */
public class GTUArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public GTUArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public GTUArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     * 
     * @param element The element to be added.
     */
    public void add(E element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Retrieves the element at the specified index.
     * 
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Ensures the internal array has enough capacity to add new elements.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length == 0 ? DEFAULT_CAPACITY : elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Checks if the list contains the specified element.
     * 
     * @param element Element to search for.
     * @return true if the list contains the element, false otherwise.
     */
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    return true;
                }
            } else {
                if (element.equals(elements[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the list.
     * 
     * @return A string representation of the list.
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * Returns an iterator over the elements in the list.
     * 
     * @return An iterator over the elements in the list.
     */
    @Override
    public java.util.Iterator<E> iterator() {
        return new java.util.Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return (E) elements[currentIndex++];
            }
        };
    }
}