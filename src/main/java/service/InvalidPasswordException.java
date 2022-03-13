package service;

public class InvalidPasswordException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid password!";
    }
}
