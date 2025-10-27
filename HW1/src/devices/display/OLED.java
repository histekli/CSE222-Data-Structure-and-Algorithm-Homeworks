package devices.display;

import protocols.Protocol;

public class OLED extends Display {
    public OLED(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("SPI")) {
            throw new IllegalArgumentException("OLED only supports SPI protocol.");
        }
    }

    @Override
    public String getName() {
        return "OLED";
    }
}
