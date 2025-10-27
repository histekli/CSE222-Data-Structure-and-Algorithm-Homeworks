package protocols;

public interface Protocol {
    String getProtocolName(); // Protokolün adını döndürür
    String read(); // Veri okuma işlemi
    void write(String data); // Veri yazma işlemi
}
