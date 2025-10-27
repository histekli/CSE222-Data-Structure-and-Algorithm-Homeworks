package devices.motor;

import protocols.Protocol;

public class PCA9685 extends MotorDriver {
    public PCA9685(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("I2C")) {
            throw new IllegalArgumentException("PCA9685 only supports I2C protocol.");
        }
    }

    @Override
    public String getName() {
        return "PCA9685";
    }
}
