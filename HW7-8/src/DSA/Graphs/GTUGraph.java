package DSA.Graphs;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Scanner;

import DSA.Graphs.GTUGraph;

/**
 * A simple graph with basic operations.
 */
public interface GTUGraph {
    /**
     * Adds an edge between vertices v1 and v2.
     * @param v1 First vertex ID.
     * @param v2 Second vertex ID.
     */
    Boolean setEdge(int v1, int v2);

    /**
     * Checks if an edge exists between vertices v1 and v2.
     * @param v1 First vertex ID.
     * @param v2 Second vertex ID.
     * @return True if the edge exists, false otherwise.
     */
    Boolean getEdge(int v1, int v2);

    /**
     * Get a collection of the neighbors of vertex v.
     * @param v Vertex ID.
     * @return The collection of neighbors.
     */
    Collection<Integer> getNeighbors(int v);

    /**
     * Size of the graph.
     * @return Size of the graph as an integer.
     */
    int size();

    /**
     * Reset the graph.
     * 
     */
    void reset(int size);


    /**
     * Reinitialize graph using the information in the given file.
     * @param filePath path to the file.
     * @param graph graph instance.
     */
    static void readGraph(String filePath, GTUGraph graph) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            Integer size = Integer.parseInt(scanner.nextLine().strip());
            graph.reset(size);
    
            String line[];
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().strip().split(" ");
                Integer v1 = Integer.parseInt(line[0]);
                Integer v2 = Integer.parseInt(line[1]);
    
                graph.setEdge(v1, v2);
            }
    
            scanner.close();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
            return;
        }
    }


    /**
     * Write the information of the given graph to a file.
     * @param filePath path to the file.
     * @param graph graph instance.
     */
    static void writeGraph(String filePath, GTUGraph graph) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);

            writer.write(Integer.toString(graph.size()) + "\n");

            for (int i = 0; i < graph.size(); i++) {
                for (var vertex : graph.getNeighbors(i)) {
                    if (i < vertex) {
                        writer.write(String.format("%d %d\n", i, vertex));
                    }
                }
            }

            writer.close();
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
            return;
        }
    }
}
