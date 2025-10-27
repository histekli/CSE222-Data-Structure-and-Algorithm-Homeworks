package devices.sensor;

import protocols.Protocol;

public abstract class TempSensor extends Sensor {

    public TempSensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public float getTemp() {
        return 24f;
    }

    @Override
    public String getSensType() {
        return "TempSensor";
    }

    @Override
    public abstract String data2String();
}
