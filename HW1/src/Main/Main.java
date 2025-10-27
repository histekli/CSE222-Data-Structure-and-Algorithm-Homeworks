package Main;

import core.HWSystem;
import java.util.Scanner;

public class Main {
    private static HWSystem system;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        // Sistem başlatma
        system = new HWSystem("config.txt");

        boolean running = true;
        System.out.println("Hardware System Started. Type 'help' for commands.");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            if (input.equals("exit")) {
                running = false;
                continue;
            }

            processCommand(input);
        }

        scanner.close();
        System.out.println("System terminated.");
    }

    private static void processCommand(String input) {
        String[] parts = input.split("\\s+");
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
                case "help":
                    showHelp();
                    break;

                case "list":
                    if (parts.length < 2) {
                        System.out.println("Error: Missing argument for list command");
                        return;
                    }
                    if (parts[1].equals("ports")) {
                        system.listPorts();
                    } else if (parts[1].equals("Sensor")) {
                        system.listDevices("Sensor");
                    } else if (parts[1].equals("Display")) {
                        system.listDevices("Display");
                    } else if (parts[1].equals("WirelessIO")) {
                        system.listDevices("WirelessIO");
                    } else if (parts[1].equals("MotorDriver")) {
                        system.listDevices("MotorDriver");
                    } else {
                        System.out.println("Error: Unknown device type: " + parts[1]);
                    }
                    break;

                case "adddev":
                    if (parts.length < 4) {
                        System.out.println("Error: Usage: addDev <devName> <portID> <devID>");
                        return;
                    }
                    system.addDevice(parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]));
                    break;

                case "rmdev":
                    if (parts.length < 2) {
                        System.out.println("Error: Usage: rmDev <portID>");
                        return;
                    }
                    system.removeDevice(Integer.parseInt(parts[1]));
                    break;

                case "turnon":
                    if (parts.length < 2) {
                        System.out.println("Error: Usage: turnON <portID>");
                        return;
                    }
                    system.turnOn(Integer.parseInt(parts[1]));
                    break;

                case "turnoff":
                    if (parts.length < 2) {
                        System.out.println("Error: Usage: turnOFF <portID>");
                        return;
                    }
                    system.turnOff(Integer.parseInt(parts[1]));
                    break;

                case "readsensor":
                    if (parts.length < 2) {
                        System.out.println("Error: Usage: readSensor <portID>");
                        return;
                    }
                    system.readSensor(Integer.parseInt(parts[1]));
                    break;

                case "printdisplay":
                    if (parts.length < 3) {
                        System.out.println("Error: Usage: printDisplay <devID> <String>");
                        return;
                    }
                    int displayID = Integer.parseInt(parts[1]);
                    String message = input.substring(input.indexOf(parts[2])); // Mesajın tamamını al
                    system.printDisplay(displayID, message);
                    break;

                case "readwireless":
                    if (parts.length < 2) {
                        System.out.println("Error: Usage: readWireless <devID>");
                        return;
                    }
                    int wirelessID = Integer.parseInt(parts[1]);
                    system.readWireless(wirelessID);
                    break;

                case "writewireless":
                    if (parts.length < 3) {
                        System.out.println("Error: Usage: writeWireless <devID> <String>");
                        return;
                    }
                    int writeWirelessID = Integer.parseInt(parts[1]);
                    String wirelessMessage = input.substring(input.indexOf(parts[2])); // Mesajın tamamını al
                    system.writeWireless(writeWirelessID, wirelessMessage);
                    break;

                case "setmotorspeed":
                    if (parts.length < 3) {
                        System.out.println("Error: Usage: setMotorSpeed <devID> <integer>");
                        return;
                    }
                    int motorID = Integer.parseInt(parts[1]);
                    int speed = Integer.parseInt(parts[2]);
                    system.setMotorSpeed(motorID, speed);
                    break;

                case "exit":
                    System.exit(0);
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for available commands.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showHelp() {
        System.out.println("Available commands:");
        System.out.println("  help                           - Show this help message");
        System.out.println("  list ports                     - List all ports");
        System.out.println("  list <deviceType>              - List devices of specific type");
        System.out.println("  addDev <devName> <portID> <devID>     - Add new device");
        System.out.println("  rmDev <portID>                - Remove device");
        System.out.println("  turnON <portID>               - Turn on device");
        System.out.println("  turnOFF <portID>              - Turn off device");
        System.out.println("  readSensor <devID>       - Read sensor data");
        System.out.println("  printDisplay <devID> <String>  - Print message to display");
        System.out.println("  setMotorSpeed <devID> <integer> - Set motor speed");
        System.out.println("  writeWireless <devID> <String> - Send wireless data");
        System.out.println("  readWireless <devID>     - Read wireless data");
        System.out.println("  exit                          - Exit program");
        System.out.println("\nDevice Types:");
        System.out.println("  Sensor      - Temperature    and  IMU  sensors ");
        System.out.println("              - (DHT11, BME280) -  (MPU6050, GY951)");
        System.out.println("  Display     - Displays (LCD, OLED)");
        System.out.println("  WirelessIO  - Wireless devices (Bluetooth, WiFi)");
        System.out.println("  MotorDriver - Motor drivers (PCA9685, SparkFunMD)");
    }
}