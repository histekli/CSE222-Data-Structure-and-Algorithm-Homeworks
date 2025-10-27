package devices.sensor;

import protocols.Protocol;

public class BME280 extends TempSensor {
    public BME280(Protocol protocol, int devID) {
        super(protocol,devID);
        String protocolName = protocol.getProtocolName();
        if (!protocolName.equals("I2C") && !protocolName.equals("SPI")) {
            throw new IllegalArgumentException("BME280 only supports I2C and SPI protocols.");
        }
    }

    @Override
    public String getName() {
        return "BME280";
    }
}
