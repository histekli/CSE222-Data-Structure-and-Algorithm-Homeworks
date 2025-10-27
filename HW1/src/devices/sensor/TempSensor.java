package devices.sensor;

import protocols.Protocol;

import java.util.Random;

public abstract class TempSensor extends Sensor {
    private static final Random random = new Random();

    public TempSensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public float getTemp() {
        return 15 + random.nextFloat() * 20;
    }

    @Override
    public String getSensType() {
        return "TempSensor";
    }

    @Override
    public String data2String() {
        return String.format("Temperature: %.2f°C", getTemp());
    }
}
