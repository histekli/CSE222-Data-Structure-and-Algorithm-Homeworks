package devices.motor;

import protocols.Protocol;

/**
 * The SparkFunMD class represents a motor driver that supports the SPI
 * protocol.
 * It allows controlling motor speed and managing the motor's state.
 */
public class SparkFunMD extends MotorDriver {

    /**
     * Constructs a SparkFunMD motor driver with the specified protocol and device
     * ID.
     *
     * @param protocol The protocol used by the motor driver (must be SPI).
     * @param devID    The unique ID of the motor driver.
     * @throws IllegalArgumentException if the protocol is not SPI.
     */
    public SparkFunMD(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("SPI")) {
            throw new IllegalArgumentException("SparkFunMD only supports SPI protocol.");
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
        return "SparkFunMD";
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
