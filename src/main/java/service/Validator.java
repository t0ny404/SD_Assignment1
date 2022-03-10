package service;

import model.User;

public class Validator {

    public void validateUsername(String username) throws InvalidUsernameException {
        if (username == null || username.length() < 8)
            throw new InvalidUsernameException();
    }

    public void validateUser(User user) throws InvalidUsernameException, InvalidUserException {
        validateUsername(user.getUsername());
        if (user.getId() == null)
            throw new InvalidUserException();
    }
}
