package org.vicary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vicary.service.dto.UserRequest;
import org.vicary.service.dto.UserResponse;
import org.vicary.entity.User;
import org.vicary.repository.UserRepository;
import org.vicary.service.map.UserMapper;

import java.util.List;

@Service                   // Oznaczanie tej klasy do wstrzykiwania zależności - dla @Autowired
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Autowired
    public UserService(
            UserRepository userRepository,
            UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public User findUserByEmail(String email) {
        if(email == null) throw new IllegalArgumentException("Email cannot be null.");
        return userRepository.findByEmail(email);
    }

    public User findUserById(Long id) {
        if(id == null) throw new IllegalArgumentException("Id cannot be null.");
        return userRepository.findById(id).get();
    }

    public UserResponse findResponseByEmail(String email) {
        if(email == null) throw new IllegalArgumentException("Email cannot be null.");
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








