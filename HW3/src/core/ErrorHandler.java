package core;

public class ErrorHandler {
    public void handleError(String message) {
        if (message.equals("Device is not active.")) {
            System.err.println(message);
            System.err.println("Command failed.");
        } else if (message.equals("Device is active.")) {
            System.err.println(message);
            System.err.println("Command failed.");
        } else {
            System.err.println("Error: " + message);
        }
    }
}