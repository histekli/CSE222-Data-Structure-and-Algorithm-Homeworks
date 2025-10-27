import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a planetary system with a hierarchical structure of stars,
 * planets, and moons.
 * Provides methods to create the system, add planets and satellites, and
 * analyze data.
 */
public class PlanetarySystem {
    private TreeNode root;

    /**
     * Creates a planetary system with a star as the root node.
     * 
     * @param starName    The name of the star.
     * @param temperature The temperature of the star.
     * @param pressure    The pressure of the star.
     * @param humidity    The humidity of the star (must be 0).
     * @param radiation   The radiation level of the star.
     * @throws IllegalStateException    If the planetary system already exists.
     * @throws IllegalArgumentException If the humidity is not 0.
     */
    public void createPlanetSystem(String starName, double temperature, double pressure, double humidity,
            double radiation) {
        if (root != null) {
            throw new IllegalStateException("Planetary system already exists!");
        }
        if (humidity != 0) {
            throw new IllegalArgumentException("Humidity must be 0 for a star!");
        }
        SensorData sensorData = new SensorData(temperature, pressure, 0, radiation);
        root = new TreeNode(starName, "Star", sensorData);
        System.out.println("Planetary system created with star: " + starName);
    }

    /**
     * Adds a planet to the planetary system.
     * 
     * @param planetName  The name of the planet.
     * @param parentName  The name of the parent node (star or planet).
     * @param temperature The temperature of the planet.
     * @param pressure    The pressure of the planet.
     * @param humidity    The humidity of the planet.
     * @param radiation   The radiation level of the planet.
     * @throws IllegalArgumentException If the parent is not a star or planet.
     */
    public void addPlanet(String planetName, String parentName, double temperature, double pressure, double humidity,
            double radiation) {
        TreeNode parent = findNode(root, parentName);
        if (parent == null || parent.getType().equals("Moon")) {
            throw new IllegalArgumentException("Parent must be a star or planet!");
        }
        SensorData sensorData = new SensorData(temperature, pressure, humidity, radiation);
        parent.addChild(new TreeNode(planetName, "Planet", sensorData));
        System.out.println("Planet added: " + planetName);
    }

    /**
     * Adds a satellite (moon) to a planet in the planetary system.
     * 
     * @param satelliteName The name of the satellite.
     * @param parentName    The name of the parent planet.
     * @param temperature   The temperature of the satellite.
     * @param pressure      The pressure of the satellite.
     * @param humidity      The humidity of the satellite.
     * @param radiation     The radiation level of the satellite.
     * @throws IllegalArgumentException If the parent is not a planet.
     */
    public void addSatellite(String satelliteName, String parentName, double temperature, double pressure,
            double humidity, double radiation) {
        TreeNode parent = findNode(root, parentName);
        if (parent == null || !parent.getType().equals("Planet")) {
            throw new IllegalArgumentException("Parent must be a planet!");
        }
        SensorData sensorData = new SensorData(temperature, pressure, humidity, radiation);
        parent.addChild(new TreeNode(satelliteName, "Moon", sensorData));
        System.out.println("Satellite added: " + satelliteName);
    }

    /**
     * Finds nodes with radiation levels exceeding the specified threshold.
     * 
     * @param threshold The radiation threshold.
     * @return A list of nodes with radiation anomalies.
     */
    public List<TreeNode> findRadiationAnomalies(double threshold) {
        return findRadiationAnomaliesRecursive(root, threshold, new ArrayList<>());
    }

    private List<TreeNode> findRadiationAnomaliesRecursive(TreeNode node, double threshold, List<TreeNode> anomalies) {
        if (node == null)
            return anomalies; // Eğer düğüm null ise, mevcut listeyi döndür

        // Önce childPlanet üzerinde DFS uygula
        if (node.getChildPlanet() != null) {
            findRadiationAnomaliesRecursive(node.getChildPlanet(), threshold, anomalies);
        }

        // Mevcut düğümün radyasyon değerini kontrol et
        if (node.getSensorData().getRadiation() > threshold) {
            anomalies.add(node); // Eğer eşik değeri aşıyorsa, listeye ekle
        }

        // Daha sonra moons listesindeki her bir düğüm üzerinde DFS uygula
        for (TreeNode moon : node.getMoons()) {
            findRadiationAnomaliesRecursive(moon, threshold, anomalies);
        }

        return anomalies; // Güncellenmiş listeyi döndür
    }

    /**
     * Gets the path from the root to the specified node.
     * 
     * @param nodeName The name of the target node.
     * @return A stack representing the path to the node.
     * @throws IllegalArgumentException If the node is not found.
     */
    public Stack<String> getPathTo(String nodeName) {
        Stack<String> path = new Stack<>();
        if (findPath(root, nodeName, path)) {
            return path;
        }
        throw new IllegalArgumentException("Node not found!");
    }

    private boolean findPath(TreeNode node, String nodeName, Stack<String> path) {
        if (node == null) {
            return false; // Eğer düğüm null ise, yolu bulamayız
        }

        path.push(node.getName()); // Mevcut düğümü yola ekle

        if (node.getName().equals(nodeName)) {
            return true; // Aranan düğüm bulundu
        }

        // Önce childPlanet üzerinde arama yap
        if (node.getChildPlanet() != null && findPath(node.getChildPlanet(), nodeName, path)) {
            return true;
        }

        // Daha sonra moons listesindeki her bir düğüm üzerinde arama yap
        for (TreeNode moon : node.getMoons()) {
            if (findPath(moon, nodeName, path)) {
                return true;
            }
        }

        path.pop(); // Eğer yol bulunamazsa, mevcut düğümü yoldan çıkar
        return false;
    }

    /**
     * Prints a mission report for the entire planetary system.
     */
    public void printMissionReport() {
        printMissionReportRecursive(root, 0);
    }

    private void printMissionReportRecursive(TreeNode node, int depth) {
        if (node == null) {
            return; // Eğer düğüm null ise, işlem yapma
        }

        // Düğümün adını, türünü ve sensör verilerini uygun bir formatta yazdır
        System.out.println("  ".repeat(depth) + node.getName() + " (" + node.getType() + "): " + node.getSensorData());

        // Önce uyduları yazdır
        for (TreeNode moon : node.getMoons()) {
            printMissionReportRecursive(moon, depth + 1);
        }

        // Eğer childPlanet varsa, onu da yazdır
        if (node.getChildPlanet() != null) {
            printMissionReportRecursive(node.getChildPlanet(), depth + 1);
        }
    }

    /**
     * Prints a mission report for a specific node in the planetary system.
     * 
     * @param nodeName The name of the node.
     * @throws IllegalArgumentException If the node is not found.
     */
    public void printMissionReport(String nodeName) {
        TreeNode node = findNode(root, nodeName);
        if (node == null) {
            throw new IllegalArgumentException("Node not found!");
        }
        System.out.println(node + ": " + node.getSensorData());
    }

    private TreeNode findNode(TreeNode node, String name) {
        if (node == null)
            return null;
        if (node.getName().equals(name))
            return node;
        if (node.getChildPlanet() != null) {
            TreeNode result = findNode(node.getChildPlanet(), name);
            if (result != null)
                return result;
        }
        for (TreeNode moon : node.getMoons()) {
            TreeNode result = findNode(moon, name);
            if (result != null)
                return result;
        }
        return null;
    }
}