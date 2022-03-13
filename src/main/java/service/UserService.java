package service;

import model.User;
import model.Package;
import repository.UserRepository;
import service.Utils.InvalidPasswordException;
import service.Utils.InvalidUserException;
import service.Utils.InvalidUsernameException;
import service.Utils.Validator;

import javax.persistence.RollbackException;
import java.util.List;

public class UserService {

    private final Validator validator = new Validator();
    private final UserRepository userRepository = new UserRepository();
    private User activeUser;

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

        activeUser = user;
        return user.getFirstname();
    }

    public User book(Package pack) {
        activeUser.addPackage(pack);
        userRepository.update(activeUser);
        return activeUser;
    }

    public Object[][] myBookings() {
        return activeUser.getPackages().stream().map(Package::getPackage).toArray(Object[][]::new);
    }
}
