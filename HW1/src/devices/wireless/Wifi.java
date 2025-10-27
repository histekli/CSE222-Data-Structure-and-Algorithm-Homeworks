package devices.wireless;

import protocols.Protocol;

public class Wifi extends WirelessIO {
    public Wifi(Protocol protocol, int devID) {
        super(protocol, devID);
        String protocolName = protocol.getProtocolName();
        if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
            throw new IllegalArgumentException("WiFi only supports SPI and UART protocols.");
        }
    }

    @Override
    public String getName() {
        return "WiFi";
    }
}
