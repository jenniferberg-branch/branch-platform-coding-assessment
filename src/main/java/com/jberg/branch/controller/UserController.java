package com.jberg.branch.controller;

import com.jberg.branch.model.UserData;
import com.jberg.branch.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET endpoint /user to retrieve GitHub user information and repositories by username.
     *
     * @param username - username of the GitHub user
     * @return ResponseEntity<UserData>
     * @throws Exception
     */
    @GetMapping("/user")
    public ResponseEntity<UserData> getUserData(@RequestParam() String username) throws Exception {
        UserData userData = userService.getUserData(username);

        return ResponseEntity.ok(userData);
    }
}
