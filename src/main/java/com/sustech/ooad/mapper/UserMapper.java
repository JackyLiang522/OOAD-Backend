package com.sustech.ooad.mapper;

import com.sustech.ooad.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt( "id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        return new User(id, name, email, password);
    }
}
