package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Result;
import com.sustech.ooad.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ClientController {

    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("login")
    public Result login(String email, String password) {
        if (clientService.isUserExist(email) &&
            clientService.getPasswordByEmailAddress(email).equals(password)) {
            return new Result(1);
        } else {
            return new Result(2);
        }
    }

    @PostMapping("/register")
    public Result register(String email, String password, String username, boolean isTeacher) {
        if (clientService.isUserExist(email)) {
            return new Result(2);
        } else {
            clientService.addUser(email, password, username, isTeacher);
            return new Result(1);
        }
    }
}

