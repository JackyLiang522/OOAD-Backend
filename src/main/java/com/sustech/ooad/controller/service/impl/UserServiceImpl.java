package com.sustech.ooad.controller.service.impl;


import com.sustech.ooad.controller.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean isUserExist(String address) {
        return false;
    }

    @Override
    public String getPasswordByUserAddress(String address) {
        return null;
    }
}
