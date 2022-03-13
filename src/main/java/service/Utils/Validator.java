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
            Double.parseDouble(price);
            Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            throw new InvalidDestinationException();
        }
    }

    public Date validateDate(String date) throws InvalidDestinationException {
        InvalidDestinationException e = new InvalidDestinationException("Invalid date!");
        if (date == null || date.length() < 8)
            throw e;

        int day, month, year, ytm, mtd;

        if (date.charAt(4) == '-') {
            ytm = 4;
        } else throw e;

        if (date.charAt(ytm+2) == '-') {
            mtd = ytm + 2;
        } else if (date.charAt(ytm+3) == '-') {
            mtd = ytm + 3;
        } else throw e;

        try {
            day = Integer.parseInt(date.substring(mtd));
            month = Integer.parseInt(date.substring(ytm + 1, mtd));
            year = Integer.parseInt(date.substring(0, ytm));
        } catch (NumberFormatException ex) {
            throw e;
        }

        Date d = new Date(year-1900, month-1, day);
         if (d.before(new Date(new java.util.Date().getTime())))
             throw e;

         return d;
    }
}
