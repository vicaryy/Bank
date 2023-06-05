package org.vicary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vicary.service.dto.UserRequest;
import org.vicary.service.UserService;
import org.vicary.service.dto.UserResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(path = "api/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest user) {
        service.createUser(user);
    }

    @DeleteMapping(path = "api/user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@RequestParam Long id) {
        service.deleteUserById(id);
    }

    @GetMapping(path = "api/user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserResponse> findUserByEmail(@RequestParam String email) {
        UserResponse user = service.findResponseByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "api/user/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserResponse> showUsers() {
        return service.showUsers();
    }
}
