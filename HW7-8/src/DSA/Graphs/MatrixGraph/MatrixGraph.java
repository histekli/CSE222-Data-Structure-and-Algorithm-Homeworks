package DSA.Graphs.MatrixGraph;

import DSA.Graphs.GTUGraph;
import java.util.Collection;

/**
 * Implements a graph using an adjacency matrix.
 */
public class MatrixGraph implements GTUGraph {
    private AdjacencyVect[] adjMatrix;
    private int numVertices;

    public MatrixGraph() {
        this.numVertices = 0;
        this.adjMatrix = new AdjacencyVect[0];
    }

    public MatrixGraph(int numVertices) {
        reset(numVertices);
    }

    @Override
    public void reset(int size) {
        this.numVertices = size;
        this.adjMatrix = new AdjacencyVect[size];
        for (int i = 0; i < size; i++) {
            adjMatrix[i] = new AdjacencyVect(size);
        }
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public Boolean getEdge(int v1, int v2) {
        return adjMatrix[v1].contains(v2);
    }

    @Override
    public Boolean setEdge(int v1, int v2) {
        return adjMatrix[v1].add(v2) && adjMatrix[v2].add(v1);
    }

    @Override
    public Collection<Integer> getNeighbors(int v) {
        return adjMatrix[v];
    }

    /**
     * @complexity All methods are O(1) except reset, which is O(n^2).
     */
}