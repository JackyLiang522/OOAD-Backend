package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Result;
import com.sustech.ooad.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private UserService userService;

    @PostMapping("/login")
    public Result login(String address, String password) {
        if (userService.isUserExist(address) &&
            userService.getPasswordByUserAddress(address).equals(password)) {
            return new Result(1);
        } else {
            return new Result(2);
        }
    }
}
