package core;

import protocols.Protocol;
import devices.Device;

public class Port {
    private int portId;
    private Protocol protocol;
    private Device connectedDevice;
    private boolean isOccupied;

    public Port(int portId, Protocol protocol) {
        this.portId = portId;
        this.protocol = protocol;
        this.isOccupied = false;
        this.connectedDevice = null;
    }

    public boolean connectDevice(Device device) {
        if (!isOccupied) {
            connectedDevice = device;
            isOccupied = true;
            return true;
        }
        return false;
    }

    public boolean disconnectDevice() {
        if (isOccupied && connectedDevice != null) {
            if (connectedDevice.getState()) { // isOn() yerine getState() kullanıldı
                return false; // Cannot disconnect while device is ON
            }
            connectedDevice = null;
            isOccupied = false;
            return true;
        }
        return false;
    }

    public int getPortId() {
        return portId;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public Device getConnectedDevice() {
        return connectedDevice;
    }

    public boolean isOccupied() {
        return isOccupied;
    }


@Override
public String toString() {
    if (isOccupied && connectedDevice != null) {
        return String.format("%d %s occupied %s %s Sensor %d %s", 
            portId, 
            protocol.getProtocolName(), 
            connectedDevice.getName(),
            connectedDevice.getDevType(),
            0,  // Sensor ID
            connectedDevice.getState() ? "ON" : "OFF");
    }
    return String.format("%d %s empty", portId, protocol.getProtocolName());
}
}