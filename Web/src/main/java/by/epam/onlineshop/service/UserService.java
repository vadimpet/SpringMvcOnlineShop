package by.epam.onlineshop.service;

import by.epam.onlineshop.model.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}