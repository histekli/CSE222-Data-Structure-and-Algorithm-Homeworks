package devices.sensor;

import protocols.Protocol;

public class GY951 extends IMUSensor {
    public GY951(Protocol protocol, int devID) {
        super(protocol, devID);
        String protocolName = protocol.getProtocolName();
        if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
            throw new IllegalArgumentException("GY951 only supports SPI and UART protocols.");
        }
    }

    @Override
    public String getName() {
        return "GY951";
    }
}
