package devices.motor;

import protocols.Protocol;

public class SparkFunMD extends MotorDriver {
    public SparkFunMD(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("SPI")) {
            throw new IllegalArgumentException("SparkFunMD only supports SPI protocol.");
        }
    }

    @Override
    public String getName() {
        return "SparkFunMD";
    }
}
