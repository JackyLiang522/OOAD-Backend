package com.sustech.ooad.controller.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean isUserExist(String address);

    public String getPasswordByUserAddress(String address);
}
