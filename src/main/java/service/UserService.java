package service;

import model.User;
import repository.UserRepository;

public class UserService {

    private final Validator validator = new Validator();
    private final UserRepository userRepository = new UserRepository();

    public void insert(User user) throws InvalidUserException, InvalidUsernameException {
        validator.validateUser(user);
        userRepository.insert(user);
    }

    public User getByUsername(String username) throws InvalidUsernameException {
        validator.validateUsername(username);
        return userRepository.findByUsername(username);
    }
}
