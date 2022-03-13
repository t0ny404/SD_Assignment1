package service.Utils;

import java.sql.Date;

public class Validator {

    public void validateUsername(String username) throws InvalidUsernameException {
        if (username == null || username.length() < 4)
            throw new InvalidUsernameException();
    }

    public void validatePassword(String password) throws InvalidPasswordException {
        if (password == null || password.length() < 8)
            throw new InvalidPasswordException();
    }

    public void validateUser(String username, String password, String firstname, String lastname, String email, String age) throws InvalidUsernameException, InvalidUserException, InvalidPasswordException {
        int ageI;
        try {
            ageI = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new InvalidUserException("Invalid age!");
        }

        validateUsername(username);
        validatePassword(password);
        if (firstname == null || lastname == null || email == null)
            throw new InvalidUserException("Invalid name!");
        if (ageI < 18)
            throw new InvalidUserException("Invalid age!");
    }

    public void validateDestination(String city, String country, String hotel, Boolean safe) throws InvalidDestinationException {
        if (city == null || country == null || hotel == null || safe == null)
            throw new InvalidDestinationException();
    }

    public void validatePackage(String name, String price, String limit, String details) throws InvalidDestinationException {
        if (name == null || price == null || limit == null)
            throw new InvalidDestinationException();

        try {
            if (Double.parseDouble(price) < 0 || Integer.parseInt(limit) <= 0)
                throw new InvalidDestinationException();
        } catch (NumberFormatException e) {
            throw new InvalidDestinationException();
        }
    }

    public Date validateDate(String date) throws InvalidDestinationException {
        InvalidDestinationException e = new InvalidDestinationException("Invalid date!");
        if (date == null || date.length() < 8)
            throw e;

        Date d;
        try {
            d = Date.valueOf(date);
        } catch (IllegalArgumentException exception) {
            throw e;
        }

         if (d.before(new Date(new java.util.Date().getTime())))
             throw e;

         return d;
    }
}
