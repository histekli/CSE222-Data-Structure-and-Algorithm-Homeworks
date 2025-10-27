package devices.wireless;

import protocols.Protocol;

public class Bluetooth extends WirelessIO {
    public Bluetooth(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("UART")) {
            throw new IllegalArgumentException("Bluetooth only supports UART protocol.");
        }
    }

    @Override
    public void sendData(String data) {
        if (!getState()) {
            System.err.println(getName() + " is OFF. Cannot send data.");
            return;
        }
        // !protocol.write("Bluetooth Sending: " + data);
        System.out.println(getName() + ": Sending \"" + data + "\".");
    }

    @Override
    public void recvData() {
        if (!getState()) {
            System.err.println(getName() + " is OFF. Cannot send data.");
            return;
        }
        // !String data = protocol.read();
        System.out.println(getName() + ": Received  \"Some Data\".");
    }

    @Override
    public String getName() {
        return "Bluetooth";
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
