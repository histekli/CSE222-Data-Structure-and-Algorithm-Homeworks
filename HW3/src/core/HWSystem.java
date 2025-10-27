package core;

import java.util.ArrayList;
import protocols.*;
import java.util.Iterator;
import devices.*;
import devices.sensor.*;
import devices.display.*;
import devices.wireless.*;
import devices.motor.*;

/**
 * The HWSystem class represents a hardware system that manages various devices
 * such as sensors, displays, wireless devices, and motor drivers. It provides
 * functionality to initialize the system, process commands, and manage devices
 * connected to ports.
 */
public class HWSystem {
    private ArrayList<Port> ports;
    private ArrayList<Sensor> sensors;
    private ArrayList<Display> displays;
    private ArrayList<WirelessIO> wirelessDevices;
    private ArrayList<MotorDriver> motorDrivers;
    private String logPath;
    private int maxSensors;
    private int maxDisplays;
    private int maxWireless;
    private int maxMotors;
    private int currentSensors;
    private int currentDisplays;
    private int currentWireless;
    private int currentMotors;
    private ErrorHandler errorHandler;
    private ConfigReader configReader;

    /**
     * Constructs an HWSystem instance with the specified configuration file and log
     * path.
     *
     * @param configFile The path to the configuration file.
     * @param logPath    The path to the log file.
     */
    public HWSystem(String configFile, String logPath) {
        this.logPath = logPath;
        ports = new ArrayList<>();
        sensors = new ArrayList<>();
        displays = new ArrayList<>();
        wirelessDevices = new ArrayList<>();
        motorDrivers = new ArrayList<>();
        currentSensors = 0;
        currentDisplays = 0;
        currentWireless = 0;
        currentMotors = 0;
        errorHandler = new ErrorHandler();
        configReader = new ConfigReader(configFile);
        initializeSystem();
    }

    /**
     * Initializes the hardware system by reading the configuration and setting up
     * ports and limits.
     */
    private void initializeSystem() {
        try {
            configReader.readConfig();
            ArrayList<Protocol> protocols = configReader.getProtocols();

            // Portları başlat
            for (int i = 0; i < protocols.size(); i++) {
                ports.add(new Port(i, protocols.get(i)));
            }

            // Varsayılan değerleri ayarla
            maxSensors = 0;
            maxDisplays = 0;
            maxWireless = 0;
            maxMotors = 0;

            // Limitleri ayarla
            ArrayList<String[]> limits = configReader.getDeviceLimits();
            for (Iterator<String[]> it = limits.iterator(); it.hasNext();) {
                String[] limit = it.next();
                int value = Integer.parseInt(limit[1]);
                switch (limit[0]) {
                    case "sensors":
                        maxSensors = value;
                        break;
                    case "displays":
                        maxDisplays = value;
                        break;
                    case "wireless adapters":
                        maxWireless = value;
                        break;
                    case "motor drivers":
                        maxMotors = value;
                        break;
                }
            }
        } catch (Exception e) {
            errorHandler.handleError("System initialization failed: " + e.getMessage());
        }
    }

    /**
     * Processes a command string and performs the corresponding action.
     *
     * @param command The command string to process.
     */
    public void processCommand(String command) {
        String[] parts = command.split("\\s+"); // Komutu boşluklara göre ayır
        String action = parts[0].toLowerCase(); // İlk kelime komut türü

        try {
            switch (action) {
                case "list":
                    if (parts.length != 2) {
                        errorHandler.handleError("Invalid format for 'list' command. Usage: list <ports|deviceType>");
                        return;
                    }
                    if (parts[1].equalsIgnoreCase("ports")) {
                        listPorts();
                    } else {
                        listDevices(parts[1]);
                    }
                    break;

                case "adddev":
                    if (parts.length != 4) {
                        errorHandler.handleError(
                                "Invalid format for 'addDev' command. Usage: addDev <devName> <portID> <devID>");
                        return;
                    }
                    addDevice(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    break;

                case "rmdev":
                    if (parts.length != 2) {
                        errorHandler.handleError("Invalid format for 'rmDev' command. Usage: rmDev <portID>");
                        return;
                    }
                    removeDevice(Integer.parseInt(parts[1]));
                    break;

                case "turnon":
                    if (parts.length != 2) {
                        errorHandler.handleError("Invalid format for 'turnOn' command. Usage: turnOn <portID>");
                        return;
                    }
                    turnOn(Integer.parseInt(parts[1]));
                    break;

                case "turnoff":
                    if (parts.length != 2) {
                        errorHandler.handleError("Invalid format for 'turnOff' command. Usage: turnOff <portID>");
                        return;
                    }
                    turnOff(Integer.parseInt(parts[1]));
                    break;

                case "readsensor":
                    if (parts.length != 2) {
                        errorHandler.handleError("Invalid format for 'readSensor' command. Usage: readSensor <devID>");
                        return;
                    }
                    readSensor(Integer.parseInt(parts[1]));
                    break;

                case "printdisplay":
                    if (parts.length != 3) {
                        errorHandler.handleError(
                                "Invalid format for 'printDisplay' command. Usage: printDisplay <devID> <message>");
                        return;
                    }
                    printDisplay(Integer.parseInt(parts[1]), parts[2]);
                    break;

                case "readwireless":
                    if (parts.length != 2) {
                        errorHandler
                                .handleError("Invalid format for 'readWireless' command. Usage: readWireless <devID>");
                        return;
                    }
                    readWireless(Integer.parseInt(parts[1]));
                    break;

                case "writewireless":
                    if (parts.length != 3) {
                        errorHandler.handleError(
                                "Invalid format for 'writeWireless' command. Usage: writeWireless <devID> <message>");
                        return;
                    }
                    writeWireless(Integer.parseInt(parts[1]), parts[2]);
                    break;

                case "setmotorspeed":
                    if (parts.length != 3) {
                        errorHandler.handleError(
                                "Invalid format for 'setMotorSpeed' command. Usage: setMotorSpeed <devID> <speed>");
                        return;
                    }
                    setMotorSpeed(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;

                default:
                    errorHandler.handleError("Unknown command: " + command);
            }
        } catch (NumberFormatException e) {
            errorHandler.handleError("Invalid number format in command: " + command);
        } catch (Exception e) {
            errorHandler.handleError("Error processing command: " + command + " - " + e.getMessage());
        }
    }

    private void printOccupiedPort(int portId, Port port) {
        Device device = port.getConnectedDevice();
        System.out.printf("%d %s occupied %s %s %d %s%n",
                portId,
                port.getProtocol().getProtocolName(),
                device.getName(),
                device.getDevType(),
                device.getDevID(),
                device.getState() ? "ON" : "OFF");
    }

    private void printEmptyPort(int portId, Port port) {
        System.out.printf("%d %s empty%n", portId, port.getProtocol().getProtocolName());
    }

    /**
     * Lists all ports and their statuses (occupied or empty).
     */
    public void listPorts() {
        System.out.println("list of ports:");
        for (Iterator<Port> it = ports.iterator(); it.hasNext();) {
            Port port = it.next();
            if (port.isOccupied()) {
                printOccupiedPort(port.getPortId(), port);
            } else {
                printEmptyPort(port.getPortId(), port);
            }
        }
    }

    /**
     * Lists all devices of a specified type.
     *
     * @param deviceType The type of devices to list (e.g., "sensor", "display").
     */
    public void listDevices(String deviceType) {
        System.out.println("list of " + deviceType + "s:");
        switch (deviceType.toLowerCase()) {
            case "sensor":
                for (Iterator<Sensor> it = sensors.iterator(); it.hasNext();) {
                    Sensor sensor = it.next();
                    Port port = findPortByDevice(sensor);
                    if (port != null) {
                        System.out.printf("%s %d %d %s %s%n",
                                sensor.getName(),
                                sensor.getDevID(),
                                port.getPortId(),
                                port.getProtocol().getProtocolName(),
                                sensor.getState() ? "ON" : "OFF");
                    } else {
                        errorHandler.handleError("Port not found for sensor: " + sensor.getName());
                    }
                }
                break;

            case "display":
                for (Iterator<Display> it = displays.iterator(); it.hasNext();) {
                    Display display = it.next();
                    Port port = findPortByDevice(display);
                    if (port != null) {
                        System.out.printf("%s %d %d %s %s%n",
                                display.getName(),
                                display.getDevID(),
                                port.getPortId(),
                                port.getProtocol().getProtocolName(),
                                display.getState() ? "ON" : "OFF");
                    } else {
                        errorHandler.handleError("Port not found for display: " + display.getName());
                    }
                }
                break;

            case "wirelessio":
                for (Iterator<WirelessIO> it = wirelessDevices.iterator(); it.hasNext();) {
                    WirelessIO wireless = it.next();
                    Port port = findPortByDevice(wireless);
                    if (port != null) {
                        System.out.printf("%s %d %d %s %s%n",
                                wireless.getName(),
                                wireless.getDevID(),
                                port.getPortId(),
                                port.getProtocol().getProtocolName(),
                                wireless.getState() ? "ON" : "OFF");
                    } else {
                        errorHandler.handleError("Port not found for wireless device: " + wireless.getName());
                    }
                }
                break;

            case "motordriver":
                for (Iterator<MotorDriver> it = motorDrivers.iterator(); it.hasNext();) {
                    MotorDriver motor = it.next();
                    Port port = findPortByDevice(motor);
                    if (port != null) {
                        System.out.printf("%s %d %d %s %s%n",
                                motor.getName(),
                                motor.getDevID(),
                                port.getPortId(),
                                port.getProtocol().getProtocolName(),
                                motor.getState() ? "ON" : "OFF");
                    } else {
                        errorHandler.handleError("Port not found for motor driver: " + motor.getName());
                    }
                }
                break;

            default:
                errorHandler.handleError("Unknown device type: " + deviceType);
        }
    }

    /**
     * Adds a device to a specified port.
     *
     * @param devName The name of the device.
     * @param portID  The ID of the port to connect the device to.
     * @param devID   The ID of the device.
     */
    public void addDevice(String devName, int portID, int devID) {
        if (!isValidPort(portID)) {
            errorHandler.handleError("Invalid port ID: " + portID);
            return;
        }

        Port port = ports.get(portID);
        if (port.isOccupied()) {
            errorHandler.handleError("Port " + portID + " is already occupied");
            return;
        }

        // Cihazı oluştur ve porta bağla
        Device device = null;
        switch (getBaseTypeFromName(devName)) {
            case "Sensor":
                Sensor sensor = createSensor(devName, port.getProtocol(), devID);
                if (sensor != null) {
                    sensors.add(sensor); // Sensör listesine ekle
                    device = sensor;
                }
                break;

            case "Display":
                Display display = createDisplay(devName, port.getProtocol(), devID);
                if (display != null) {
                    displays.add(display); // Display listesine ekle
                    device = display;
                }
                break;

            case "WirelessIO":
                WirelessIO wireless = createWirelessIO(devName, port.getProtocol(), devID);
                if (wireless != null) {
                    wirelessDevices.add(wireless); // WirelessIO listesine ekle
                    device = wireless;
                }
                break;

            case "MotorDriver":
                MotorDriver motor = createMotorDriver(devName, port.getProtocol(), devID);
                if (motor != null) {
                    motorDrivers.add(motor); // MotorDriver listesine ekle
                    device = motor;
                }
                break;

            default:
                errorHandler.handleError("Unknown device type: " + devName);
                return;
        }

        if (device != null) {
            port.connectDevice(device); // Port ile cihazı bağla
            System.out.println("Device added: " + devName + " with ID " + devID + " to port " + portID);
        } else {
            errorHandler.handleError("Failed to create device: " + devName);
        }
    }

    /**
     * Removes a device from a specified port.
     *
     * @param portId The ID of the port to disconnect the device from.
     */
    public void removeDevice(int portId) {
        if (!isValidPort(portId)) {
            errorHandler.handleError("Invalid port ID: " + portId);
            return;
        }

        Port port = ports.get(portId);
        Device device = port.getConnectedDevice();

        if (device == null) {
            errorHandler.handleError("No device connected to port " + portId);
            return;
        }

        if (device.getState()) {
            errorHandler.handleError("Device is active.");
            return;
        }

        if (port.disconnectDevice()) {
            Iterator<Sensor> sensorIt = sensors.iterator();
            while (sensorIt.hasNext()) {
                if (sensorIt.next() == device) {
                    sensorIt.remove();
                    break;
                }
            }

            Iterator<Display> displayIt = displays.iterator();
            while (displayIt.hasNext()) {
                if (displayIt.next() == device) {
                    displayIt.remove();
                    break;
                }
            }

            Iterator<WirelessIO> wirelessIt = wirelessDevices.iterator();
            while (wirelessIt.hasNext()) {
                if (wirelessIt.next() == device) {
                    wirelessIt.remove();
                    break;
                }
            }

            Iterator<MotorDriver> motorIt = motorDrivers.iterator();
            while (motorIt.hasNext()) {
                if (motorIt.next() == device) {
                    motorIt.remove();
                    break;
                }
            }

            System.out.println("Device removed.");
        }
    }

    /**
     * Turns on the device connected to a specified port.
     *
     * @param portId The ID of the port.
     */
    public void turnOn(int portId) {
        try {
            if (!isValidPort(portId)) {
                errorHandler.handleError("Invalid port ID: " + portId);
                return;
            }

            Port port = ports.get(portId);
            Device device = port.getConnectedDevice();

            if (device == null) {
                errorHandler.handleError("No device connected to port " + portId);
                return;
            }

            device.turnON();

            // Loglama işlemi
            port.logWrite(" Turn ON");
        } catch (Exception e) {
            errorHandler.handleError("Error turning on device: " + e.getMessage());
        }
    }

    /**
     * Turns off the device connected to a specified port.
     *
     * @param portId The ID of the port.
     */
    public void turnOff(int portId) {
        try {
            if (!isValidPort(portId)) {
                errorHandler.handleError("Invalid port ID: " + portId);
                return;
            }

            Port port = ports.get(portId);
            Device device = port.getConnectedDevice();

            if (device == null) {
                errorHandler.handleError("No device connected to port " + portId);
                return;
            }

            device.turnOFF();

            // Loglama işlemi
            port.logWrite(" Turn OFF");
        } catch (Exception e) {
            errorHandler.handleError("Error turning off device: " + e.getMessage());
        }
    }

    /**
     * Reads data from a sensor with the specified device ID.
     *
     * @param devID The ID of the sensor device.
     */
    public void readSensor(int devID) {
        Iterator<Sensor> it = sensors.iterator();
        while (it.hasNext()) {
            Sensor sensor = it.next();
            if (sensor.getDevID() == devID) {
                Port port = findPortByDevice(sensor);
                if (port != null) {
                    port.logRead();
                }
                if (!sensor.getState()) {
                    errorHandler.handleError("Sensor is not active.");
                    return;
                }
                System.out.println(sensor.data2String());
                return;
            }
        }
        errorHandler.handleError("No sensor found with ID: " + devID);
    }

    /**
     * Prints a message to a display with the specified device ID.
     *
     * @param devID   The ID of the display device.
     * @param message The message to print.
     */
    public void printDisplay(int devID, String message) {
        Iterator<Display> it = displays.iterator();
        while (it.hasNext()) {
            Display display = it.next();
            if (display.getDevID() == devID) {
                Port port = findPortByDevice(display);
                if (port != null) {
                    port.logWrite(message);
                }
                display.printData(message);
                return;
            }
        }
        errorHandler.handleError("Display with ID " + devID + " not found.");
    }

    /**
     * Reads data from a wireless device with the specified device ID.
     *
     * @param devID The ID of the wireless device.
     */
    public void readWireless(int devID) {
        Iterator<WirelessIO> it = wirelessDevices.iterator();
        while (it.hasNext()) {
            WirelessIO wireless = it.next();
            if (wireless.getDevID() == devID) {
                Port port = findPortByDevice(wireless);
                if (port != null) {
                    port.logRead();
                }
                wireless.recvData();
                return;
            }
        }
        errorHandler.handleError("Wireless device with ID " + devID + " not found.");
    }

    /**
     * Sends a message to a wireless device with the specified device ID.
     *
     * @param devID   The ID of the wireless device.
     * @param message The message to send.
     */
    public void writeWireless(int devID, String message) {
        Iterator<WirelessIO> it = wirelessDevices.iterator();
        while (it.hasNext()) {
            WirelessIO wireless = it.next();
            if (wireless.getDevID() == devID) {
                Port port = findPortByDevice(wireless);
                if (port != null) {
                    port.logWrite(message);
                }
                wireless.sendData(message);
                return;
            }
        }
        errorHandler.handleError("Wireless device with ID " + devID + " not found.");
    }

    /**
     * Sets the speed of a motor driver with the specified device ID.
     *
     * @param devID The ID of the motor driver.
     * @param speed The speed to set.
     */
    public void setMotorSpeed(int devID, int speed) {
        Iterator<MotorDriver> it = motorDrivers.iterator();
        while (it.hasNext()) {
            MotorDriver motor = it.next();
            if (motor.getDevID() == devID) {
                Port port = findPortByDevice(motor);
                if (port != null) {
                    port.logWrite("" + speed);
                }
                if (!motor.getState()) {
                    errorHandler.handleError("Motor is OFF. Cannot set speed.");
                    return;
                }
                motor.setMotorSpeed(speed);
                return;
            }
        }
        errorHandler.handleError("Motor with ID " + devID + " not found.");
    }

    private boolean isValidPort(int portId) {
        return portId >= 0 && portId < ports.size();
    }

    private Port findPortByDevice(Device device) {
        if (device == null) {
            return null;
        }

        int devID = device.getDevID(); // Cihazın devID'sini al
        for (Iterator<Port> it = ports.iterator(); it.hasNext();) {
            Port port = it.next();
            Device connectedDevice = port.getConnectedDevice();
            if (connectedDevice != null && connectedDevice.getDevID() == devID) {
                return port; // devID eşleşiyorsa portu döndür
            }
        }
        return null; // Hiçbir eşleşme bulunamazsa null döndür
    }

    private void decrementDeviceCount(String deviceType) {
        switch (deviceType) {
            case "TempSensor":
                currentSensors--;
                break;
            case "Display":
                currentDisplays--;
                break;
            case "WirelessIO":
                currentWireless--;
                break;
            case "MotorDriver":
                currentMotors--;
                break;
        }
    }

    private Sensor createSensor(String devName, Protocol protocol, int devID) {
        switch (devName.toUpperCase()) {
            case "DHT11":
                if (protocol instanceof OneWire) {
                    return new DHT11(protocol, devID);
                }
                errorHandler.handleError("DHT11 requires OneWire protocol");
                break;

            case "BME280":
                if (protocol instanceof I2C || protocol instanceof SPI) {
                    return new BME280(protocol, devID);
                }
                errorHandler.handleError("BME280 requires I2C or SPI protocol");
                break;

            case "MPU6050":
                if (protocol instanceof I2C) {
                    return new MPU6050(protocol, devID);
                }
                errorHandler.handleError("MPU6050 requires I2C protocol");
                break;

            case "GY951":
                if (protocol instanceof SPI || protocol instanceof UART) {
                    return new GY951(protocol, devID);
                }
                errorHandler.handleError("GY951 requires SPI or UART protocol");
                break;

            default:
                errorHandler.handleError("Unknown sensor: " + devName);
                break;
        }
        return null;
    }

    private Display createDisplay(String devName, Protocol protocol, int devID) {
        switch (devName.toUpperCase()) {
            case "LCD":
                if (protocol instanceof I2C) {
                    return new LCD(protocol, devID);
                }
                errorHandler.handleError("LCD requires I2C protocol");
                break;

            case "OLED":
                if (protocol instanceof SPI) {
                    return new OLED(protocol, devID);
                }
                errorHandler.handleError("OLED requires SPI protocol");
                break;

            default:
                errorHandler.handleError("Unknown display: " + devName);
                break;
        }
        return null;
    }

    private WirelessIO createWirelessIO(String devName, Protocol protocol, int devID) {
        switch (devName.toUpperCase()) {
            case "BLUETOOTH":
                if (protocol instanceof UART) {
                    return new Bluetooth(protocol, devID);
                }
                errorHandler.handleError("Bluetooth requires UART protocol");
                break;

            case "WIFI":
                if (protocol instanceof SPI || protocol instanceof UART) {
                    return new Wifi(protocol, devID);
                }
                errorHandler.handleError("WiFi requires SPI or UART protocol");
                break;

            default:
                errorHandler.handleError("Unknown wireless device: " + devName);
                break;
        }
        return null;
    }

    private MotorDriver createMotorDriver(String devName, Protocol protocol, int devID) {
        switch (devName.toUpperCase()) {
            case "PCA9685":
                if (protocol instanceof I2C) {
                    return new PCA9685(protocol, devID);
                }
                errorHandler.handleError("PCA9685 requires I2C protocol");
                break;

            case "SPARKFUNMD":
                if (protocol instanceof SPI) {
                    return new SparkFunMD(protocol, devID);
                }
                errorHandler.handleError("SparkFunMD requires SPI protocol");
                break;

            default:
                errorHandler.handleError("Unknown motor driver: " + devName);
                break;
        }
        return null;
    }

    private Device createDevice(String devName, Protocol protocol, int devID) {
        String baseType = getBaseTypeFromName(devName);
        switch (baseType) {
            case "Sensor":
                return createSensor(devName, protocol, devID);
            case "Display":
                return createDisplay(devName, protocol, devID);
            case "WirelessIO":
                return createWirelessIO(devName, protocol, devID);
            case "MotorDriver":
                return createMotorDriver(devName, protocol, devID);
            default:
                errorHandler.handleError("Unknown device type: " + devName);
                return null;
        }
    }

    private String getBaseTypeFromName(String devName) {
        switch (devName.toUpperCase()) {
            case "LCD":
            case "OLED":
                return "Display";
            case "DHT11":
            case "BME280":
            case "MPU6050":
            case "GY951":
                return "Sensor";
            case "WIFI":
            case "BLUETOOTH":
                return "WirelessIO";
            case "PCA9685":
            case "SPARKFUNMD":
                return "MotorDriver";
            default:
                return "Unknown";
        }
    }

    /**
     * Closes all ports and logs the closure.
     */
    public void closePorts() {
        for (Iterator<Port> it = ports.iterator(); it.hasNext();) {
            Port port = it.next();
            port.close(logPath);
        }
    }
}