package org.vicary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vicary.dto.UserRequest;
import org.vicary.dto.UserResponse;
import org.vicary.model.User;
import org.vicary.repository.UserRepository;

import java.util.List;

@Service                   // Oznaczanie tej klasy do wstrzykiwania zależności - dla @Autowired
public class UserService {
    UserRepository userRepository;
    UserMapper mapper;

    @Autowired
    UserService(
            UserRepository userRepository,
            UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public UserResponse findResponseByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapper.map(user);
    }

    public void createUser(UserRequest userRequest) {
        User user = mapper.map(userRequest);
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserResponse> showUsers() {
        List<User> users = userRepository.findAll();
        return mapper.map(users);
    }

}








