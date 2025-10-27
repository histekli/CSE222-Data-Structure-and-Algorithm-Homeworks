package DSA.Graphs.GCA;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import DSA.Graphs.MatrixGraph.AdjacencyVect;

/**
 * A class representing a graph coloring solution. 
 */
public class GCASolution {
    private List<Collection<Integer>> colors;
    private int vNum;

    public GCASolution(int vNum) {
        colors = new ArrayList<>();
        this.vNum = vNum;
    }

    /**
     * Assign vertex v to color c.
     * @param v Vertex ID.
     * @param c Color ID.
     */
    public void setColor(int v, int c) {
        colors.get(c).add(v);
    }

    /**
     * Add a new color to the solution.
     * @return new color ID.
     */
    public Integer addColor() {
        colors.add(new AdjacencyVect(vNum));
        return colors.size() - 1;
    }

    /**
     * Get the number of colors.
     * @return number of colors.
     */
    public Integer colorNum() {
        return colors.size();
    }

    /**
     * Get a collection containing all vertices assigned to color c. 
     * @param c color ID.
     * @return collection of vertex IDs.
     */
    public Collection<Integer> getColorVertices(int c) {
        return colors.get(c);
    }

    /**
     * Write the solution to the a file.
     * @param filePath Path to destination file.
     * @return true if successful, false otherwise.
     */
    public Boolean writeSolution(String filePath) {
        FileWriter writer;
        try {
            writer = new FileWriter(filePath);

            writer.write(String.format("%d\n%d\n", vNum, colors.size()));

            for (int i = 0; i < colorNum(); i++) {
                for (var vertex : getColorVertices(i)) {
                    writer.write(String.format("%d %d\n", i, vertex));
                }
            }

            writer.close();
            return true;
        } catch (Exception e) {
            System.err.printf("Error: %s\n", e.getMessage());
            return false;
        }
    }
}
