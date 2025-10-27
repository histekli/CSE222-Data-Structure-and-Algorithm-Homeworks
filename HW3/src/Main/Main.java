package Main;

import core.HWSystem;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // 1. Yapılandırma dosyasını kontrol et
        if (args.length < 2) {
            System.err.println("Error: Configuration file and log path are required.");
            return;
        }

        String configFile = args[0];
        String logPath = args[1];
        HWSystem system = new HWSystem(configFile, logPath);

        // 2. Komutları saklamak için bir kuyruk oluştur
        Queue<String> commandQueue = new LinkedList<>();

        // 3. Terminalden komutları oku
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            commandQueue.add(command);
        }
        scanner.close();

        // 4. Komutları sırayla işle
        while (!commandQueue.isEmpty()) {
            String command = commandQueue.poll();
            system.processCommand(command); // Komutları HWSystem'e gönder
        }

        // 5. Port loglarını yaz ve çıkış mesajı
        system.closePorts();
        System.out.println("Exiting ...");
    }
}