package devices.display;

import devices.Device;
import protocols.Protocol;

/**
 * The Display class is an abstract base class for display devices.
 * It provides common functionality for displays, such as printing data.
 */
public abstract class Display extends Device {

    /**
     * Constructs a Display with the specified protocol and device ID.
     *
     * @param protocol The protocol used by the display.
     * @param devID    The unique ID of the display.
     */
    public Display(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Prints data to the display.
     *
     * @param data The data to print.
     */
    public abstract void printData(String data);

    /**
     * Returns the type of the device as "Display".
     *
     * @return The type of the device.
     */
    @Override
    public String getDevType() {
        return "Display";
    }

    /**
     * Returns the base type of the device as "Display".
     *
     * @return The base type of the device.
     */
    @Override
    public String getBaseType() {
        return "Display";
    }
}
