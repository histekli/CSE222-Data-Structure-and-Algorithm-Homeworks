package DSA.Graphs.GCA;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import DSA.Graphs.GTUGraph;
import DSA.Sorting.GTUSorter;

/**
 * A greedy graph coloring algorithm.
 */
public class GreedyGCA {
    /**
     * A simple vertex representation for use in the algorithm.
     */
    private static class GreedyVertex implements Comparable<GreedyVertex> {
        private int id;
        private Collection<Integer> neighbors;

        public GreedyVertex(int id) {
            this.id = id;
        }

        @Override
        public int compareTo(GreedyVertex o) {
            if (this.neighbors.size() - o.neighbors.size() == 0) {
                return this.id - o.id;
            } else {
                return this.neighbors.size() - o.neighbors.size();
            }
        }

        public int getID() {
            return id;
        }

        public void setNeighbors(Collection<Integer> neighbors) {
            this.neighbors = neighbors;
        }

        public Collection<Integer> getNeighbors() {
            return neighbors;
        }
    }

    /**
     * Return a graph coloring solution for the graph.
     * @param graph The graph to be colored.
     * @param sorter The sorter class that will sort the vertices.
     * @return A graph coloring solution.
     */
    public static GCASolution solve(GTUGraph graph, GTUSorter sorter) {
        // Create a list of GreedyVertex instances.
        GreedyVertex[] vertices = new GreedyVertex[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            var newVertex = new GreedyVertex(i);
            newVertex.setNeighbors(graph.getNeighbors(i));
            vertices[i] = newVertex;
        }

        // Sort the vertices.
        sorter.sort(vertices, new Comparator<GreedyVertex>() {
            @Override
            public int compare(GreedyVertex arg0, GreedyVertex arg1) {
                return arg1.compareTo(arg0);
            }
        });

        // Iterate over each vertex, and assign it to the smallest available color.
        GCASolution solution = new GCASolution(graph.size());
        Boolean isColored;
        for (GreedyVertex vertex : vertices) {
            isColored = false;
            for (int i = 0; i < solution.colorNum(); i++) {
                // If the vertex's neighbors and the color's vertices are disjoint, assign the vertex to this color.
                if (Collections.disjoint(solution.getColorVertices(i), vertex.getNeighbors())) {
                    solution.setColor(vertex.getID(), i);
                    isColored = true;
                    break;
                }
            }

            // Create a new color if no available color was found.
            if (!isColored) {
                Integer newColor = solution.addColor();
                solution.setColor(vertex.getID(), newColor);
            }
        }

        return solution;
    }
}
