package protocols;

public class OneWire implements Protocol {
    @Override
    public String getProtocolName() {
        return "OneWire";
    }

    @Override
    public String read() {
        return "OneWire: Reading.";
    }

    @Override
    public void write(String data) {
        System.out.println("OneWire: Writing \"" + data + "\".");
    }
}