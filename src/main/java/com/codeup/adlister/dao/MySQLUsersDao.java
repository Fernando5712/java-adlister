package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        try {
            PreparedStatement stmt = connection.prepareStatement(String.format("SELECT username, email, password FROM users WHERE username = '%s'", username));
            ResultSet rs = stmt.executeQuery();
            rs.next();
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Long insert(User user) {
        Long next = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(String.format("INSERT INTO users(username, email, password) VALUES ('%s', '%s', '%s')",
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword()
            ), Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            next = rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return next;
    }
}
