import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * The Main class serves as the entry point for the application.
 * It provides a command-line interface for interacting with the
 * PlanetarySystem.
 */
public class Main {
    /**
     * The main method initializes the PlanetarySystem and processes user commands.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        PlanetarySystem system = new PlanetarySystem();
        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("Enter command:");
                String command = scanner.nextLine();
                String[] parts = command.split(" ");
                try {
                    switch (parts[0]) {
                        case "createPlanetSystem":
                            system.createPlanetSystem(parts[1], Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3]),
                                    Double.parseDouble(parts[4]),
                                    Double.parseDouble(parts[5]));
                            break;
                        case "addPlanet":
                            system.addPlanet(parts[1], parts[2], Double.parseDouble(parts[3]),
                                    Double.parseDouble(parts[4]),
                                    Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
                            break;
                        case "addSatellite":
                            system.addSatellite(parts[1], parts[2], Double.parseDouble(parts[3]),
                                    Double.parseDouble(parts[4]), Double.parseDouble(parts[5]),
                                    Double.parseDouble(parts[6]));
                            break;
                        case "findRadiationAnomalies":
                            List<TreeNode> anomalies = system.findRadiationAnomalies(Double.parseDouble(parts[1]));
                            anomalies.forEach(node -> System.out.println(node));
                            break;
                        case "getPathTo":
                            Stack<String> path = system.getPathTo(parts[1]);
                            System.out.println(path);
                            break;
                        case "printMissionReport":
                            if (parts.length == 1) {
                                system.printMissionReport();
                            } else {
                                system.printMissionReport(parts[1]);
                            }
                            break;
                        case "exit":
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Unknown command!");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}