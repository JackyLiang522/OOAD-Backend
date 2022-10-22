package com.sustech.ooad.mapper;

import com.sustech.ooad.entity.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt( "id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        return new Client(id, name, email, password);
    }
}
