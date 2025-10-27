package devices.display;

import protocols.Protocol;

public class LCD extends Display {
    public LCD(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("I2C")) {
            throw new IllegalArgumentException("LCD only supports I2C protocol.");
        }
    }

    @Override
    public String getName() {
        return "LCD";
    }
}
