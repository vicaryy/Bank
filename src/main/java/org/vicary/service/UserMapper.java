package org.vicary.service;

import org.springframework.stereotype.Component;
import org.vicary.dto.UserRequest;
import org.vicary.dto.UserResponse;
import org.vicary.model.Account;
import org.vicary.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse map(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .accounts(user.getAccounts()
                        .stream()
                        .map(Account::getAccount_id)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<UserResponse> map(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users)
            userResponses.add(map(user));
        return userResponses;
    }

    public User map(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .lastname(userRequest.getLastname())
                .email(userRequest.getEmail())
                .build();
    }
}
