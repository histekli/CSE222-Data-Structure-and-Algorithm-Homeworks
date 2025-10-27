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
}
