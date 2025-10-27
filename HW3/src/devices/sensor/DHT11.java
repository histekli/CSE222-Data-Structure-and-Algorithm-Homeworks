package devices.sensor;

import protocols.Protocol;

public class DHT11 extends TempSensor {
    public DHT11(Protocol protocol, int devID) {
        super(protocol, devID);
        if (!protocol.getProtocolName().equals("OneWire")) {
            throw new IllegalArgumentException("DHT11 only supports OneWire protocol.");
        }
    }

    @Override
    public String getName() {
        return "DHT11";
    }

    @Override
    public String data2String() {
        return String.format("%s %s: Temp: %.2f", getName(), getDevType(), getTemp());
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
