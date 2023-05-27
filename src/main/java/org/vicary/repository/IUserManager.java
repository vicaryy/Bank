package org.vicary.repository;

import org.vicary.model.User;

public interface IUserManager {
    void addUser(User user);
    User findUserByEmail(String email);
    void deleteUser(User user);
    void transferFunds(User from, User to, double amount);
}
