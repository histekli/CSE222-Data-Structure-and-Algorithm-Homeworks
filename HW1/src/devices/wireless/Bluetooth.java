package devices.wireless;

import protocols.Protocol;

public class Bluetooth extends WirelessIO {
    public Bluetooth(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("UART")) {
            throw new IllegalArgumentException("Bluetooth only supports UART protocol.");
        }
    }
    @Override
    public String getName() {
        return "Bluetooth";
    }
}
