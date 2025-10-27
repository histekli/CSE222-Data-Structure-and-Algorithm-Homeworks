package devices.sensor;

import protocols.Protocol;

public class GY951 extends IMUSensor {
    public GY951(Protocol protocol, int devID) {
        super(protocol, devID);
        String protocolName = protocol.getProtocolName();
        if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
            throw new IllegalArgumentException("GY951 only supports SPI and UART protocols.");
        }
    }

    @Override
    public String getName() {
        return "GY951";
    }

    @Override
    public String data2String() {
        return String.format("%s %s: Accel: %.2f, Rot: %.2f", getName(), getDevType(), getAccel(), getRot());
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
}
