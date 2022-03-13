package service;

public class InvalidDestinationException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid destination/package!";
    }
}
