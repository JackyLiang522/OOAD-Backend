package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.repository.ClientRepository;
import com.sustech.ooad.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public boolean isUserExist(String address) {
        Client user = clientRepository.findByEmail(address).stream().findAny().orElse(null);
        return user != null;
    }

    @Override
    public String getPasswordByEmailAddress(String address) {
        Client user = clientRepository.findByEmail(address).stream().findAny().orElse(null);
        if (user == null) {
            return null;
        } else {
            return user.getPassword();
        }
    }

    @Override
    public Client addUser(String email, String password, String username, boolean isTeacher) {
        Client client = new Client(username, email, password, isTeacher, false);
        return clientRepository.save(client);
    }
}
