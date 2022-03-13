package service;

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

    public void validatePackage(Integer destId, String name, String price, String limit, String details) throws InvalidDestinationException {
        if (destId == null || name == null || price == null || limit == null)
            throw new InvalidDestinationException();

        try {
            Double.parseDouble(price);
            Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            throw new InvalidDestinationException();
        }
    }

    public Date validateDate(String date) throws InvalidDestinationException {
        if (date == null || date.length() < 8)
            throw new InvalidDestinationException();

        int day, month, year, dtm, mty;

        if (date.charAt(1) == '/') {
            dtm = 1;
        } else if (date.charAt(2) == '/') {
            dtm = 2;
        } else throw new InvalidDestinationException();

        if (date.charAt(dtm+2) == '/') {
            mty = dtm + 2;
        } else if (date.charAt(dtm+3) == '/') {
            mty = dtm + 3;
        } else throw new InvalidDestinationException();

        try {
            day = Integer.parseInt(date.substring(0, dtm));
            month = Integer.parseInt(date.substring(dtm + 1, mty));
            year = Integer.parseInt(date.substring(mty + 1));
        } catch (NumberFormatException e) {
            throw new InvalidDestinationException();
        }

        return new Date(year, month, day);
    }
}
