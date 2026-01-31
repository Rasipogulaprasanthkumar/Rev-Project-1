package com.revconnect.service;

import com.revconnect.DAO.UserDAO;
import com.revconnect.model.User;

import java.util.regex.Pattern;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    private static final Pattern GMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@gmail\\.com$");

    public boolean Register(User user) {

        if (!isValidGmail(user.getEmail())) {
            System.out.println("Enter the Valid Email ❌");
            return false;
        }

        if (userDAO.findByEmail(user.getEmail()) != null) {
            System.out.println("User already exists ❌");
            return false;
        }

        return userDAO.register(user);
    }

    public User login(String email, String password) {

        User user = userDAO.findByEmail(email);

        if (user == null) {
            System.out.println("User Not Found ❌");
            return null;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Invalid Password ❌");
            return null;
        }

        return user;
    }

    private boolean isValidGmail(String email) {
        return email != null && GMAIL_PATTERN.matcher(email).matches();
    }
}
