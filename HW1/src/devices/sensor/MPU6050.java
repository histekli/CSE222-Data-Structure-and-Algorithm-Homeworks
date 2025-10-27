package devices.sensor;

import protocols.Protocol;

public class MPU6050 extends IMUSensor {
    public MPU6050(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("I2C")) {
            throw new IllegalArgumentException("MPU6050 only supports I2C protocol.");
        }
    }
    @Override
    public String getName() {
        return "MPU6050";
    }
}
