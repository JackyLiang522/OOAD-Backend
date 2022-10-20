package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Result;
import com.sustech.ooad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public Result login(String address, String password) {
        if (userService.isUserExist(address) &&
            userService.getPasswordByEmailAddress(address).equals(password)) {
            return new Result(1);
        } else {
            return new Result(2);
        }
    }
}
