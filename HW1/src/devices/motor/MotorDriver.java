package devices.motor;

import devices.Device;
import protocols.Protocol;

public abstract class MotorDriver extends Device {
    public MotorDriver(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public void setMotorSpeed(int speed) {
        if (state) {
            protocol.write("Setting motor speed to " + speed);
        } else {
            System.out.println(getName() + " is OFF. Cannot set motor speed.");
        }
    }

    @Override
    public String getDevType() {
        return "MotorDriver";
    }

    @Override
    public String getBaseType() {
        return "MotorDriver";
    }
}
