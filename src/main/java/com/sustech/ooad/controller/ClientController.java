package com.sustech.ooad.controller;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public Client login(String email, String password) {
        Client client = clientService.getUserByEmail(email);
        if (client != null && client.getPassword().equals(password)) {
            return client;
        } else {
            return null;
        }
    }

    @PostMapping("/register")
    public Client register(String email, String password, String username, boolean isTeacher) {
        if (clientService.isUserExist(email)) {
            return null;
        } else {
            return clientService.addUser(email, password, username, isTeacher);
        }
    }

    @GetMapping("")
    public Client getUserById(Long id) {
        return clientService.getUserById(id);
    }
}

