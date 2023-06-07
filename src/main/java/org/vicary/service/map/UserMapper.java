package org.vicary.service.map;

import org.springframework.stereotype.Component;
import org.vicary.service.dto.UserRequest;
import org.vicary.service.dto.UserResponse;
import org.vicary.entity.Account;
import org.vicary.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse map(User user) {
        List<Long> userIds = null;
        if(user.getAccounts() != null) userIds = user.getAccounts().stream().map(Account::getAccount_id).collect(Collectors.toList());
        return UserResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .accounts(userIds)
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
