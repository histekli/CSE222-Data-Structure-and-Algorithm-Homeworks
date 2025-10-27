package core;

public class ErrorHandler {
    public void handleError(String message) {
        if (message.equals("Device is not active.")) {
            System.out.println(message);
            System.out.println("Command failed.");
        } else if (message.equals("Device is active.")) {
            System.out.println(message);
            System.out.println("Command failed.");
        } else {
            System.out.println("Error: " + message);
        }
    }
}