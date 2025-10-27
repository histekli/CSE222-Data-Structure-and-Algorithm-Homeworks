package devices;

import protocols.Protocol;

public abstract class Device {
    protected Protocol protocol;
    private int devID;
    protected boolean state;

    public Device(Protocol protocol, int devID) {
        this.protocol = protocol;
        this.devID = devID;
        this.state = false;
    }

    public abstract String getName();

    public abstract String getDevType();

    public abstract String getBaseType();

    public abstract void turnON();

    public abstract void turnOFF();

    public boolean getState() {
        return state;
    }

    public int getDevID() {
        return devID;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public boolean isSensor() {
        return false; // Varsayılan olarak false
    }
}