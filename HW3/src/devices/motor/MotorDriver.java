package devices.motor;

import devices.Device;
import protocols.Protocol;

/**
 * The MotorDriver class is an abstract base class for motor driver devices.
 * It provides common functionality for motor drivers, such as setting motor
 * speed.
 */
public abstract class MotorDriver extends Device {

    /**
     * Constructs a MotorDriver with the specified protocol and device ID.
     *
     * @param protocol The protocol used by the motor driver.
     * @param devID    The unique ID of the motor driver.
     */
    public MotorDriver(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Sets the speed of the motor.
     *
     * @param speed The speed to set for the motor.
     */
    public abstract void setMotorSpeed(int speed);

    /**
     * Returns the type of the device as "MotorDriver".
     *
     * @return The type of the device.
     */
    @Override
    public String getDevType() {
        return "MotorDriver";
    }

    /**
     * Returns the base type of the device as "MotorDriver".
     *
     * @return The base type of the device.
     */
    @Override
    public String getBaseType() {
        return "MotorDriver";
    }
}
