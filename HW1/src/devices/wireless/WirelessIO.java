package devices.wireless;

import devices.Device;
import protocols.Protocol;

public abstract class WirelessIO extends Device {
    public WirelessIO(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public void sendData(String data) {
        if (state) {
            protocol.write("Sending: " + data);
        } else {
            System.out.println(getName() + " is OFF. Cannot send data.");
        }
    }

    public String recvData() {
        if (state) {
            return protocol.read();
        } else {
            return getName() + " is OFF. Cannot receive data.";
        }
    }

    @Override
    public String getDevType() {
        return "WirelessIO";
    }

    @Override
    public String getBaseType() {
        return "WirelessIO";
    }
}
