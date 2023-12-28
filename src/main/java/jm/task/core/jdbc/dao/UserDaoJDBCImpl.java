package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String SQL_QUERY_GET_ALL_USERS = "SELECT id, name, lastName, age FROM users";
    private static final String SQL_QUERY_SAVE_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?) ";
    private static final String SQL_QUERY_REMOVE_USER = "DELETE FROM users WHERE id=?";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement prepStmt = conn.prepareStatement(SQL_QUERY_SAVE_USER)) {
            prepStmt.setString(1, name);
            prepStmt.setString(2, lastName);
            prepStmt.setByte(3, age);
            prepStmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getMySQLConnection();
             PreparedStatement prepStmt = conn.prepareStatement(SQL_QUERY_REMOVE_USER)) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_QUERY_GET_ALL_USERS)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR: " + e);
        }
        return users;
    }

    public void cleanUsersTable() {

    }
}
