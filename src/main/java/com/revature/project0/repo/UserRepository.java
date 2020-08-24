package com.revature.project0.repo;

import com.revature.project0.models.User;
import com.revature.project0.util.ConnectionFactory;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepository {

    public UserRepository() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    public Optional<User> findUserByCredentials(String username, String password) {
        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project0.app_users au " +
                         "JOIN project0.user_roles ur " +
                         "ON au.role_id = ur.id " +
                         "WHERE username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }

    public Optional<User> findUserByUsername(String username) {

        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM project_0 WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {

            sqle.printStackTrace();

        }

        return _user;
    }

    public void save(User newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project_0.user (first_name, last_name, username, email, password) " +
                    "VALUES (?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
            pstmt.setString(1, newUser.getFirstName());
            pstmt.setString(2, newUser.getLastName());
            pstmt.setString(3, newUser.getUsername());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getPassword());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                newUser.setId(rs.getInt(1));
            }

        } catch (SQLException sqle) {
            sqle. printStackTrace();
        }
    }

    private Set<User> mapResultSet(ResultSet rs) throws SQLException {

        Set<User> users = new HashSet<>();

        while (rs.next()) {
            User temp = new User();
            temp.setId(rs.getInt("id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            temp.setUsername(rs.getString("username"));
            temp.setEmail(rs.getString("password"));
        }

        return users;
    }

}
