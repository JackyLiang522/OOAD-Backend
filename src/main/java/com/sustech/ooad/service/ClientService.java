package com.sustech.ooad.service;

import com.sustech.ooad.entity.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    public boolean isUserExist(String email);

    public String getPasswordByEmailAddress(String email);

    public Client addUser(String email, String password, String username, boolean isTeacher);
}
