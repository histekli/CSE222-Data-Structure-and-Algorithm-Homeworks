package protocols;

public class SPI implements Protocol {
    @Override
    public String getProtocolName() {
        return "SPI";
    }

    @Override
    public String read() {
        return "SPI: Reading.";
    }

    @Override
    public void write(String data) {
        System.out.println("SPI: Writing \"" + data + "\".");
    }
}
