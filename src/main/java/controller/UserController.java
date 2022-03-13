package controller;

import service.InvalidPasswordException;
import service.InvalidUserException;
import service.InvalidUsernameException;
import service.UserService;

import java.util.Arrays;

public class UserController {

    private final UserService userService = new UserService();

    public String register(String username, char[] password, String firstname, String lastname, String email, String age) {
        try {
            userService.insert(username, Arrays.toString(password)
                    .replace(", ", "").substring(1).replace("]", ""), firstname, lastname, email, age);
        } catch (InvalidPasswordException | InvalidUsernameException | InvalidUserException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "User already exists!";
        }
        return "Success!";
    }

    public String login(String username, char[] password) {
        try {
            String user = userService.login(username, Arrays.toString(password)
                    .replace(", ", "").substring(1).replace("]", ""));
            if (user.equals("admin"))
                return user;

            return "Hello " + user + "!";
        } catch (InvalidUserException e) {
            return e.getMessage();
        }
    }
}
