package devices.display;

import devices.Device;
import protocols.Protocol;

public abstract class Display extends Device {
    public Display(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public void printData(String data) {
        if (state) {
            protocol.write("Displaying: " + data);
        } else {
            System.out.println(getName() + " is OFF. Cannot display data.");
        }
    }

    @Override
    public String getDevType() {
        return "Display";
    }

    @Override
    public String getBaseType() {
        return "Display";
    }
}
