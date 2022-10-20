package com.sustech.ooad.dao;

import com.sustech.ooad.entity.User;
import com.sustech.ooad.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserDAO(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Nullable
    public User getUserByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email = ?", userMapper, email)
            .stream()
            .findAny()
            .orElse(null);
    }
}
