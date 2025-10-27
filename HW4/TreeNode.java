import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in a planetary system tree structure.
 * Each node can represent a star, planet, or moon and may have child nodes.
 */
public class TreeNode {
    private String name;
    private String type; // Star, Planet, Moon
    private SensorData sensorData;
    private List<TreeNode> moons;
    private TreeNode childPlanet;

    /**
     * Constructs a TreeNode with the specified name, type, and sensor data.
     * 
     * @param name       The name of the node.
     * @param type       The type of the node (e.g., Star, Planet, Moon).
     * @param sensorData The sensor data associated with the node.
     */
    public TreeNode(String name, String type, SensorData sensorData) {
        this.name = name;
        this.type = type;
        this.sensorData = sensorData;
        this.moons = new ArrayList<>();
        this.childPlanet = null;
    }

    /**
     * Adds a child planet to this node.
     * 
     * @param childPlanet The child planet to add.
     * @throws IllegalArgumentException If the child is not a planet.
     * @throws IllegalStateException    If a child planet already exists.
     */
    public void addChildPlanet(TreeNode childPlanet) {
        if (!childPlanet.getType().equals("Planet")) {
            throw new IllegalArgumentException("ChildPlanet must be a planet!");
        }
        if (this.childPlanet != null) {
            throw new IllegalStateException("Child planet already exists!");
        }
        this.childPlanet = childPlanet;
    }

    /**
     * Gets the child planet of this node.
     * 
     * @return The child planet, or null if none exists.
     */
    public TreeNode getChildPlanet() {
        return childPlanet;
    }

    /**
     * Gets the name of this node.
     * 
     * @return The name of the node.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of this node.
     * 
     * @return The type of the node.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the sensor data associated with this node.
     * 
     * @return The sensor data.
     */
    public SensorData getSensorData() {
        return sensorData;
    }

    /**
     * Gets the list of moons associated with this node.
     * 
     * @return A list of moons.
     */
    public List<TreeNode> getMoons() {
        return moons;
    }

    /**
     * Adds a child node to this node. The child can be a planet or a moon.
     * 
     * @param child The child node to add.
     * @throws IllegalArgumentException If the child is not a planet or moon.
     */
    public void addChild(TreeNode child) {
        if (child.getType().equals("Planet")) {
            addChildPlanet(child);
        } else if (child.getType().equals("Moon")) {
            addMoons(child);
        } else {
            throw new IllegalArgumentException("Child must be a planet or moon!");
        }
    }

    /**
     * Adds a moon to this node.
     * 
     * @param moon The moon to add.
     * @throws IllegalArgumentException If the child is not a moon.
     */
    public void addMoons(TreeNode moon) {
        if (!moon.getType().equals("Moon")) {
            throw new IllegalArgumentException("Moon must be a moon!");
        }
        moons.add(moon);
    }

    /**
     * Returns a string representation of this node.
     * 
     * @return A string in the format "name (type)".
     */
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}