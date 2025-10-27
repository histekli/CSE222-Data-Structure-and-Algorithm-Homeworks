package devices.wireless;

import devices.Device;
import protocols.Protocol;

public abstract class WirelessIO extends Device {
    public WirelessIO(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public abstract void sendData(String data);

    public abstract void recvData();

    @Override
    public String getDevType() {
        return "WirelessIO";
    }

    @Override
    public String getBaseType() {
        return "WirelessIO";
    }
}
