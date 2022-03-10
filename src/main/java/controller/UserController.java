package controller;

import model.User;
import service.InvalidUserException;
import service.InvalidUsernameException;
import service.UserService;

public class UserController {

    private final UserService userService = new UserService();

    public void insert(User user) {
        try {
            userService.insert(user);
        } catch (InvalidUsernameException | InvalidUserException e) {
            e.printStackTrace();
        }
        System.out.println("user successfully inserted!");
    }

    public void getByUsername(String username) {
        User user = new User();
        try {
            user = userService.getByUsername(username);

        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
    }
}
