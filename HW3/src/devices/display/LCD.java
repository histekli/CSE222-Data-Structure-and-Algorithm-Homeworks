package devices.display;

import protocols.Protocol;

/**
 * The LCD class represents a display device that supports the I2C protocol.
 * It allows printing data and managing the display's state.
 */
public class LCD extends Display {

    /**
     * Constructs an LCD display with the specified protocol and device ID.
     *
     * @param protocol The protocol used by the display (must be I2C).
     * @param devID    The unique ID of the display.
     * @throws IllegalArgumentException if the protocol is not I2C.
     */
    public LCD(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("I2C")) {
            throw new IllegalArgumentException("LCD only supports I2C protocol.");
        }
    }

    /**
     * Prints data to the LCD display.
     *
     * @param data The data to print.
     */
    @Override
    public void printData(String data) {
        if (!getState()) {
            System.err.println(getName() + " is OFF. Cannot display data.");
            return;
        }
        System.out.println(getName() + ": Printing \"" + data + "\".");
    }

    /**
     * Turns on the LCD display.
     */
    @Override
    public void turnON() {
        if (!state) {
            System.out.println(getName() + ": Turning ON");
            state = true;
        }
    }

    /**
     * Turns off the LCD display.
     */
    @Override
    public void turnOFF() {
        if (state) {
            System.out.println(getName() + ": Turning OFF");
            state = false;
        }
    }

    /**
     * Returns the name of the display.
     *
     * @return The name of the display.
     */
    @Override
    public String getName() {
        return "LCD";
    }
}
