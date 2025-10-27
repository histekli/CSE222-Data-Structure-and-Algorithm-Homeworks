// ConfigReader.java
package core;

import java.io.File;
import java.util.ArrayList;
import protocols.Protocol;
import protocols.I2C;
import protocols.SPI;
import protocols.UART;
import protocols.OneWire;

public class ConfigReader {
    private String configFile;
    private ArrayList<Protocol> protocols;
    private ArrayList<String[]> deviceLimits;

    public ConfigReader(String configFile) {
        this.configFile = configFile;
        this.protocols = new ArrayList<>();
        this.deviceLimits = new ArrayList<>();
    }

    public void readConfig() throws Exception {
        File file = new File(configFile);
        java.util.Scanner scanner = new java.util.Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("Port Configuration:")) {
                parseProtocols(line);
            } else if (line.startsWith("#")) {
                parseDeviceLimit(line);
            }
        }
        scanner.close();
    }

    private void parseProtocols(String line) {
        String[] parts = line.split(":")[1].trim().split(",");
        for (String protocol : parts) {
            switch (protocol.trim()) {
                case "I2C": 
                    protocols.add(new I2C()); 
                    break;
                case "SPI": 
                    protocols.add(new SPI()); 
                    break;
                case "UART": 
                    protocols.add(new UART()); 
                    break;
                case "OneWire": 
                    protocols.add(new OneWire()); 
                    break;
            }
        }
    }

   
private void parseDeviceLimit(String line) {
    String[] parts = line.split(":");
    if (parts.length == 2) {
        String type = parts[0].trim();
        if (type.startsWith("# of ")) {
            // "# of " kısmını kaldır ve kalan kısmı al
            type = type.substring(4).trim();
            String limit = parts[1].trim();
            // Tip adını küçük harfe çevir
            deviceLimits.add(new String[]{type.toLowerCase(), limit});
        }
    }
}

    public ArrayList<Protocol> getProtocols() {
        return protocols;
    }

    public ArrayList<String[]> getDeviceLimits() {
        return deviceLimits;
    }
}