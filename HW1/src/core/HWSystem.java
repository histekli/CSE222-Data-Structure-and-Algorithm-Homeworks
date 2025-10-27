package core;

import java.util.ArrayList;
import protocols.*;
import devices.*;
import devices.sensor.*;
import devices.display.*;
import devices.wireless.*;
import devices.motor.*;

public class HWSystem {
    private ArrayList<Port> ports;
    private ArrayList<Device> devices;
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

    public HWSystem(String configFile) {
        ports = new ArrayList<>();
        devices = new ArrayList<>();
        currentSensors = 0;
        currentDisplays = 0;
        currentWireless = 0;
        currentMotors = 0;
        errorHandler = new ErrorHandler();
        configReader = new ConfigReader(configFile);
        initializeSystem();
    }

    // HWSystem.java
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
            for (String[] limit : limits) {
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

            // Debug için limitleri yazdır
            System.out.println("Device limits:");
            System.out.println("Sensors: " + maxSensors);
            System.out.println("Displays: " + maxDisplays);
            System.out.println("Wireless: " + maxWireless);
            System.out.println("Motors: " + maxMotors);

        } catch (Exception e) {
            errorHandler.handleError("System initialization failed: " + e.getMessage());
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

    public void listPorts() {
        System.out.println("list of ports:");
        for (int i = 0; i < ports.size(); i++) {
            Port port = ports.get(i);
            if (port.isOccupied()) {
                printOccupiedPort(i, port);
            } else {
                printEmptyPort(i, port);
            }
        }
    }

    public void listDevices(String deviceType) {
        System.out.println("list of " + deviceType + "s:");
        for (Device device : devices) {
            if (device.getBaseType().equals(deviceType)) {
                Port port = findPortByDevice(device);
                if (port != null) {
                    System.out.printf("%s %d %d %s%n",
                            device.getName(),
                            device.getDevID(),
                            port.getPortId(),
                            port.getProtocol().getProtocolName());
                }
            }
        }
    }

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

        for (Device device : devices) {
            if (device.getBaseType().equalsIgnoreCase(getBaseTypeFromName(devName)) && device.getDevID() == devID) {
                errorHandler.handleError(
                        "Device ID \"" + devID + "\" is already in use for device type " + device.getBaseType() + ":"
                                + device.getName());
                return;
            }
        }

        Device device = createDevice(devName, port.getProtocol(), devID);
        if (device != null) {
            port.connectDevice(device);
            devices.add(device);
            System.out.println("Device " + devName + " added to port " + portID);
        }
    }

    public void removeDevice(int portId) {
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

            if (device.getState()) {
                errorHandler.handleError("Device is active.");
                return;
            }

            if (port.disconnectDevice()) {
                String deviceType = device.getDevType();
                decrementDeviceCount(deviceType);
                devices.remove(device);
                System.out.println("Device removed from port " + portId);
            }
        } catch (Exception e) {
            errorHandler.handleError("Error removing device: " + e.getMessage());
        }
    }

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
        } catch (Exception e) {
            errorHandler.handleError("Error turning on device: " + e.getMessage());
        }
    }

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
        } catch (Exception e) {
            errorHandler.handleError("Error turning off device: " + e.getMessage());
        }
    }

    // HWSystem.java
    public void readSensor(int devID) {
        Sensor sensor = null;

        // devID'ye sahip sensörü bul
        for (Device device : devices) {
            if (device.isSensor() && device.getDevID() == devID) {
                sensor = (Sensor) device; // Artık downcasting gerekmez
                break;
            }
        }

        if (sensor == null) {
            errorHandler.handleError("No sensor found with ID: " + devID);
            return;
        }

        if (!sensor.getState()) {
            errorHandler.handleError("Device is not active.");
            return;
        }

        System.out.println(sensor.getProtocol().read());
        System.out.println(sensor.getName() + " " + sensor.getDevType() + ": " + sensor.data2String());
    }

    public void printDisplay(int devID, String message) {
        for (Device device : devices) {
            if (device instanceof Display && device.getDevID() == devID) {
                Display display = (Display) device;
                if (!display.getState()) {
                    errorHandler.handleError("Display is OFF. Cannot display data.");
                    return;
                }
                display.printData(message);
                return;
            }
        }
        errorHandler.handleError("Display with ID " + devID + " not found.");
    }

    public void readWireless(int devID) {
        for (Device device : devices) {
            if (device instanceof WirelessIO && device.getDevID() == devID) {
                WirelessIO wireless = (WirelessIO) device;
                if (!wireless.getState()) {
                    errorHandler.handleError("Wireless device is OFF. Cannot read data.");
                    return;
                }
                System.out.println(wireless.recvData());
                return;
            }
        }
        errorHandler.handleError("Wireless device with ID " + devID + " not found.");
    }

    public void writeWireless(int devID, String message) {
        for (Device device : devices) {
            if (device instanceof WirelessIO && device.getDevID() == devID) {
                WirelessIO wireless = (WirelessIO) device;
                if (!wireless.getState()) {
                    errorHandler.handleError("Wireless device is OFF. Cannot send data.");
                    return;
                }
                wireless.sendData(message);
                return;
            }
        }
        errorHandler.handleError("Wireless device with ID " + devID + " not found.");
    }

    public void setMotorSpeed(int devID, int speed) {
        for (Device device : devices) {
            if (device instanceof MotorDriver && device.getDevID() == devID) {
                MotorDriver motor = (MotorDriver) device;
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
        for (Port port : ports) {
            if (port.getConnectedDevice() == device) {
                return port;
            }
        }
        return null;
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

    private Device createDevice(String devName, Protocol protocol, int devID) {
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
                errorHandler.handleError("Unknown device: " + devName);
                break;
        }
        return null;
    }

    private String getBaseTypeFromName(String devName) {
        switch (devName.toUpperCase()) {
            case "LCD":
            case "OLED":
                return "Display";
            case "DHT11":
            case "BME280":
                return "Sensor";
            case "WIFI":
            case "BLUETOOTH":
                return "WirelessIO";
            case "SPARKFUNMD":
                return "MotorDriver";
            default:
                return "Unknown";
        }
    }
}