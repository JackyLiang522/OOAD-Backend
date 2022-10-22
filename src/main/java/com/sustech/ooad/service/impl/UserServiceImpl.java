package com.sustech.ooad.service.impl;

import com.sustech.ooad.entity.Client;
import com.sustech.ooad.repository.ClientRepository;
import com.sustech.ooad.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {
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
}
