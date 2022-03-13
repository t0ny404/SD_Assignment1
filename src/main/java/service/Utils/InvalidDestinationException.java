package service.Utils;

public class InvalidDestinationException extends Exception {

    public InvalidDestinationException() {
        super("Invalid destination/package!");
    }

    public InvalidDestinationException(String message) {
        super(message);
    }
}
