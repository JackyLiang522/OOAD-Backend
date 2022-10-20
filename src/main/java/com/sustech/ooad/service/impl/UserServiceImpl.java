package com.sustech.ooad.service.impl;

import com.sustech.ooad.dao.UserDAO;
import com.sustech.ooad.entity.User;
import com.sustech.ooad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean isUserExist(String address) {
        User user = userDAO.getUserByEmail(address);
        return user != null;
    }

    @Override
    public String getPasswordByEmailAddress(String address) {
        User user = userDAO.getUserByEmail(address);
        if (user == null) {
            return null;
        } else {
            return user.getPassword();
        }
    }
}
