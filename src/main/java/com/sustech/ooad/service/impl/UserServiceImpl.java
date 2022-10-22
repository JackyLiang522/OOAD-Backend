package com.sustech.ooad.service.impl;

import com.sustech.ooad.dao.UserDAO;
import com.sustech.ooad.entity.Client;
import com.sustech.ooad.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean isUserExist(String address) {
        Client user = userDAO.getUserByEmail(address);
        return user != null;
    }

    @Override
    public String getPasswordByEmailAddress(String address) {
        Client user = userDAO.getUserByEmail(address);
        if (user == null) {
            return null;
        } else {
            return user.getPassword();
        }
    }
}
