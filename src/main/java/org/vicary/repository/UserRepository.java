package org.vicary.repository;

import org.vicary.model.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    List<User> showUsers();
    User findUserByEmail(String email);
    void deleteUser(User user);
}
