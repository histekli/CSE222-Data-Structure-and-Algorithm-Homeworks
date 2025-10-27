package devices.display;

import protocols.Protocol;

public class OLED extends Display {
    public OLED(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("SPI")) {
            throw new IllegalArgumentException("OLED only supports SPI protocol.");
        }
    }

    @Override
    public void printData(String data) {
        if (!getState()) {
            System.err.println(getName() + " is OFF. Cannot display data.");
            return;
        }
        // ! protocol.write("Printing: " + data);
        System.out.println(getName() + ": Printing \"" + data + "\".");
    }

    @Override
    public void turnON() {
        if (!state) {
            System.out.println(getName() + ": Turning ON");
            state = true;
        }
    }

    @Override
    public void turnOFF() {
        if (state) {
            System.out.println(getName() + ": Turning OFF");
            state = false;
        }
    }

    @Override
    public String getName() {
        return "OLED";
    }
}
