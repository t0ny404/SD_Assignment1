package service;

import model.User;
import repository.UserRepository;
import service.Utils.InvalidPasswordException;
import service.Utils.InvalidUserException;
import service.Utils.InvalidUsernameException;
import service.Utils.Validator;

import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final Validator validator = new Validator();
    private final UserRepository userRepository = new UserRepository();
    private final List<User> activeUsers = new ArrayList<>();

    public void insert(String username, String password, String firstname, String lastname, String email, String age) throws InvalidUserException, InvalidUsernameException, InvalidPasswordException {
        validator.validateUser(username, password, firstname, lastname, email, age);
        try {
            userRepository.insert(new User(username, password, firstname, lastname, email, Integer.parseInt(age)));
        } catch(RollbackException e) {
            throw new InvalidUserException("Username already exists!");
        }
    }

    public String login(String username, String password) throws InvalidUserException {
        List users = userRepository.findByUsername(username);
        if (users == null || users.size() == 0)
            throw new InvalidUserException("Invalid username/password combination!");
        User user = (User) users.get(0);
        if (user == null || !user.getPassword().equals(password))
            throw new InvalidUserException("Invalid username/password combination!");

        if (!user.isCustomer())
            return "admin";

        activeUsers.add(user);
        return user.getFirstname();
    }
}
