package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // http://localhost:8081/api/client/login?email=&&password=
    @PostMapping(value = "/login")
    public Client login(@RequestParam String email, @RequestParam String password) {
        Client client = clientService.getUserByEmail(email);
        if (client != null && client.getPassword().equals(password)) {
            return client;
        } else {
            return null;
        }
    }

    // http://localhost:8081/api/client/register?email=&&password=&&username=&&isTeacher=
    @PostMapping(value = "/register")
    public Client register(@RequestParam String email, @RequestParam String password, @RequestParam String username, @RequestParam boolean isTeacher) {
        if (clientService.isUserExist(email)) {
            return null;
        } else {
            return clientService.addUser(email, password, username, isTeacher);
        }
    }

    // http://localhost:8081/api/client/getById?userId=
    @GetMapping("/getById")
    public Client getUserById(@RequestParam Long userId) {
        return clientService.getUserById(userId);
    }


}

