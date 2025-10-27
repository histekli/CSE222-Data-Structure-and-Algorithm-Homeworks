package DSA.Tests;

import DSA.Graphs.MatrixGraph.AdjacencyVect;
import DSA.Graphs.MatrixGraph.MatrixGraph;
import DSA.Sorting.*;
import java.util.Comparator;

public class ProjectTests {

    private static int testCount = 0;
    private static int passCount = 0;

    public static void main(String[] args) {
        System.out.println("--- Starting Project Tests ---");

        testAdjacencyVect();
        testMatrixGraph();
        testSortingAlgorithms();

        System.out.println("--- Project Tests Completed ---");
        System.out.printf("Total Tests: %d, Passed: %d, Failed: %d\n", testCount, passCount, testCount - passCount);
    }

    private static void assertTest(boolean condition, String message) {
        testCount++;
        if (condition) {
            passCount++;
        } else {
            System.err.println("FAILED: " + message);
        }
    }

    // Test AdjacencyVect
    private static void testAdjacencyVect() {
        System.out.println("\n-> Testing AdjacencyVect");
        AdjacencyVect vec = new AdjacencyVect(5);
        assertTest(vec.size() == 0, "New AdjacencyVect should have size 0.");
        assertTest(vec.isEmpty(), "New AdjacencyVect should be empty.");

        assertTest(vec.add(0), "Adding vertex 0 should return true.");
        assertTest(vec.contains(0), "Vertex 0 should be present.");
        assertTest(vec.size() == 1, "Size should be 1 after adding vertex 0.");

        boolean exceptionThrown = false;
        try {
            vec.add(5); // Out of bounds
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTest(exceptionThrown, "Adding out-of-bounds vertex should throw IllegalArgumentException.");
    }

    // Test MatrixGraph
    private static void testMatrixGraph() {
        System.out.println("\n-> Testing MatrixGraph");
        MatrixGraph graph = new MatrixGraph(5);

        assertTest(graph.size() == 5, "Graph size should be 5.");
        assertTest(graph.setEdge(0, 1), "Adding edge between 0 and 1 should return true.");
        assertTest(graph.getEdge(0, 1), "Edge between 0 and 1 should exist.");
        assertTest(graph.getEdge(1, 0), "Edge between 1 and 0 should exist (undirected graph).");

        assertTest(graph.getNeighbors(0).contains(1), "Vertex 1 should be a neighbor of vertex 0.");
        assertTest(graph.getNeighbors(1).contains(0), "Vertex 0 should be a neighbor of vertex 1.");
    }

    // Test Sorting Algorithms
    private static void testSortingAlgorithms() {
        System.out.println("\n-> Testing Sorting Algorithms");

        Integer[] arr = { 5, 3, 8, 1, 2 };
        Integer[] expected = { 1, 2, 3, 5, 8 };

        // Test MyInsertSort
        MyInsertSort insertSort = new MyInsertSort();
        insertSort.sort(arr, Comparator.naturalOrder());
        assertTest(java.util.Arrays.equals(arr, expected), "MyInsertSort should sort the array correctly.");

        // Test MyQuickSort
        arr = new Integer[] { 5, 3, 8, 1, 2 };
        MyQuickSort quickSort = new MyQuickSort();
        quickSort.sort(arr, Comparator.naturalOrder());
        assertTest(java.util.Arrays.equals(arr, expected), "MyQuickSort should sort the array correctly.");

        // Test MySelectSort
        arr = new Integer[] { 5, 3, 8, 1, 2 };
        MySelectSort selectSort = new MySelectSort();
        selectSort.sort(arr, Comparator.naturalOrder());
        assertTest(java.util.Arrays.equals(arr, expected), "MySelectSort should sort the array correctly.");
    }
}