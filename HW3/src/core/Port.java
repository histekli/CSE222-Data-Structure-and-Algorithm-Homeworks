package core;

import protocols.Protocol;
import devices.Device;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Port {
    private int portID;
    private Protocol protocol;
    private Device connectedDevice;
    private Stack<String> logStack; // Logları saklamak için bir Stack

    public Port(int portID, Protocol protocol) {
        this.portID = portID;
        this.protocol = protocol;
        this.connectedDevice = null;
        this.logStack = new Stack<>();
        logStack.push("Port Opened."); // İlk log
    }

    public int getPortId() {
        return portID;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public boolean isOccupied() {
        return connectedDevice != null;
    }

    public Device getConnectedDevice() {
        return connectedDevice;
    }

    public void connectDevice(Device device) {
        this.connectedDevice = device;
    }

    public boolean disconnectDevic() {
        if (connectedDevice != null) {
            connectedDevice = null;
            return true;
        }
        return false;
    }

    public boolean disconnectDevice() {
        if (!isOccupied()) {
            return false; // Port zaten boş
        }
        connectedDevice = null;
        return true;
    }

    public void logRead() {
        logStack.push("Reading."); // Okuma işlemini logla
    }

    public void logWrite(String data) {
        logStack.push("Writing \"" + data + "\"."); // Yazma işlemini logla
    }

    public void close(String logPath) {
        // Log dosyasını yaz
        String fileName = logPath + "/" + protocol.getProtocolName() + "_" + portID + ".log";
        try (FileWriter writer = new FileWriter(fileName)) {
            while (!logStack.isEmpty()) {
                writer.write(logStack.pop() + "\n"); // Stack'ten ters sırada yaz
            }
        } catch (IOException e) {
            System.err.println("Error writing log file for port " + portID + ": " + e.getMessage());
        }
    }
}