package devices.sensor;

import devices.Device;
import protocols.Protocol;

public abstract class Sensor extends Device {
    public Sensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    public abstract String getSensType(); // Sensör türünü döndürür (örn: "TempSensor", "IMUSensor")

    public abstract String data2String(); // Sensör verisini okunabilir bir string olarak döndürür

    @Override
    public String getDevType() {
        return getSensType() + " Sensor"; // Örn: "TempSensor Sensor"
    }

    @Override
    public boolean isSensor() {
        return true; // Sensor sınıfı için true döner
    }

    @Override
    public String getBaseType() {
        return "Sensor";
    }
}
