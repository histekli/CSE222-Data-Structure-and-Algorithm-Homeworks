package devices.sensor;

import protocols.Protocol;

public abstract class IMUSensor extends Sensor {

    public IMUSensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public float getAccel() {
        return 1f;
    }

    public float getRot() {
        return 0.5f;
    }

    @Override
    public String getSensType() {
        return "IMUSensor";
    }

    @Override
    public abstract String data2String();
}
