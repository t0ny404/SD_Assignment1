package service.Utils;

public class InvalidUsernameException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid username!";
    }
}
