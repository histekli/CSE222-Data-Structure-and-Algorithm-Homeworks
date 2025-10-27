package DSA.Graphs.MatrixGraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a row in an adjacency matrix.
 */
public class AdjacencyVect implements Collection<Integer> {
    private boolean[] adjacent;
    private int edgeCount;

    public AdjacencyVect(int numTotalVertices) {
        if (numTotalVertices < 0) {
            throw new IllegalArgumentException("Number of vertices cannot be negative.");
        }
        this.adjacent = new boolean[numTotalVertices];
        this.edgeCount = 0;
    }

    @Override
    public int size() {
        return edgeCount;
    }

    @Override
    public boolean isEmpty() {
        return edgeCount == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer))
            return false;
        int vertex = (Integer) o;
        return vertex >= 0 && vertex < adjacent.length && adjacent[vertex];
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                for (int i = currentIndex + 1; i < adjacent.length; i++) {
                    if (adjacent[i])
                        return true;
                }
                return false;
            }

            @Override
            public Integer next() {
                for (int i = currentIndex + 1; i < adjacent.length; i++) {
                    if (adjacent[i]) {
                        currentIndex = i;
                        return i;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public boolean add(Integer vertex) {
        if (vertex < 0 || vertex >= adjacent.length) { // Geçerli sınırları kontrol et
            throw new IllegalArgumentException("Index out of bounds: " + vertex);
        }
        if (!adjacent[vertex]) {
            adjacent[vertex] = true;
            edgeCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Integer))
            return false;
        int vertex = (Integer) o;
        if (adjacent[vertex]) {
            adjacent[vertex] = false;
            edgeCount--;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < adjacent.length; i++) {
            adjacent[i] = false;
        }
        edgeCount = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[edgeCount];
        int index = 0;
        for (int i = 0; i < adjacent.length; i++) {
            if (adjacent[i])
                result[index++] = i;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * @complexity All methods are O(n) except size, isEmpty, and clear, which are
     *             O(1).
     */
}