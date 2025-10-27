package devices.motor;

import protocols.Protocol;

/**
 * The PCA9685 class represents a motor driver that supports the I2C protocol.
 * It allows controlling motor speed and managing the motor's state.
 */
public class PCA9685 extends MotorDriver {

    /**
     * Constructs a PCA9685 motor driver with the specified protocol and device ID.
     *
     * @param protocol The protocol used by the motor driver (must be I2C).
     * @param devID    The unique ID of the motor driver.
     * @throws IllegalArgumentException if the protocol is not I2C.
     */
    public PCA9685(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("I2C")) {
            throw new IllegalArgumentException("PCA9685 only supports I2C protocol.");
        }
    }

    /**
     * Sets the speed of the motor.
     *
     * @param speed The speed to set for the motor.
     */
    @Override
    public void setMotorSpeed(int speed) {
        if (!getState()) {
            System.err.println(getName() + ": Motor is OFF. Cannot set speed.");
            return;
        }
        System.out.println(getName() + ": Setting speed to " + speed + ".");
    }

    /**
     * Returns the name of the motor driver.
     *
     * @return The name of the motor driver.
     */
    @Override
    public String getName() {
        return "PCA9685";
    }

    /**
     * Turns on the motor driver.
     */
    @Override
    public void turnON() {
        if (!state) {
            System.out.println(getName() + ": Turning ON");
            state = true;
        }
    }

    /**
     * Turns off the motor driver.
     */
    @Override
    public void turnOFF() {
        if (state) {
            System.out.println(getName() + ": Turning OFF");
            state = false;
        }
    }
}
