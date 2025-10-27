package devices.sensor;

import protocols.Protocol;

import java.util.Random;

public abstract class IMUSensor extends Sensor {
    private static final Random random = new Random();

    public IMUSensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public float getAccel() {
        return random.nextFloat() * 10; // 0 ile 10 arasında rastgele ivme üret
    }

    public float getRot() {
        return random.nextFloat() * 360; // 0 ile 360 derece arasında rastgele dönüş hızı üret
    }

    @Override
    public String getSensType() {
        return "IMUSensor";
    }

    @Override
    public String data2String() {
        return String.format("Accel: %.2f, Rot: %.2f", getAccel(), getRot());
    }
}
