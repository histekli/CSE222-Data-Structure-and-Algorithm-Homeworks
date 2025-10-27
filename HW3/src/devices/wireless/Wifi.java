package devices.wireless;

import protocols.Protocol;

public class Wifi extends WirelessIO {
    public Wifi(Protocol protocol, int devID) {
        super(protocol, devID);
        String protocolName = protocol.getProtocolName();
        if (!protocolName.equals("SPI") && !protocolName.equals("UART")) {
            throw new IllegalArgumentException("WiFi only supports SPI and UART protocols.");
        }
    }

    @Override
    public void sendData(String data) {
        if (!getState()) {
            System.err.println(getName() + " is OFF. Cannot send data.");
            return;
        }
        // !protocol.write("WiFi Sending: " + data);
        System.out.println(getName() + ": Sending \"" + data + "\".");
    }

    @Override
    public void recvData() {
        if (!getState()) {
            System.out.println(getName() + " is OFF. Cannot receive data.");
        }
        // ! String data = protocol.read();
        System.out.println(getName() + ": Received \"Some Data\".");
    }

    @Override
    public String getName() {
        return "WiFi";
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
