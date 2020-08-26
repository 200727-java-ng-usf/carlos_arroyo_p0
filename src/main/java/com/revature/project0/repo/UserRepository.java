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
//        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    public Optional<User> findUserByCredentials(String username, String password) {
        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project_0.customer " +
                         "WHERE username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            // find the first user that matches
            _user = mapResultSet(rs).stream().findFirst();

            return _user;

        } catch (SQLException sqle) {
            System.err.println("Database error1");
        }

        return _user;
    }

    public Optional<User> findUserByUsername(String username) {

        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM project_0.customer";
            PreparedStatement pstmt = conn.prepareStatement(sql);


            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                User newUser = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
                if (newUser.getUsername().equals(username)) {
                    _user = Optional.of(newUser);
                    return _user;
                }
            }

        } catch (SQLException sqle) {

            System.err.println("database error2");

        }

        return _user;
    }

//    public void save(User newUser) {
//
//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            String sql = "INSERT INTO project_0.customer (first_name, last_name, username, email, password) " +
//                    "VALUES (?,?,?,?,?)";
//
//            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
//            pstmt.setString(1, newUser.getFirstName());
//            pstmt.setString(2, newUser.getLastName());
//            pstmt.setString(3, newUser.getUsername());
//            pstmt.setString(4, newUser.getEmail());
//            pstmt.setString(5, newUser.getPassword());
//
//            int rowsInserted = pstmt.executeUpdate();
//
//            if (rowsInserted != 0) {
//
//                ResultSet rs = pstmt.getGeneratedKeys();
//
//                rs.next();
//                newUser.setId(rs.getInt(1));
//            }
//
//        } catch (SQLException sqle) {
//            sqle. printStackTrace();
//        }
//    }

    public void save(User newUser) {


        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            //insert the user into the table
            String sql = "INSERT INTO project_0.customer (first_name, last_name, username, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"}); //retrieve autogenerated values


            pstmt.setString(1, newUser.getFirstName());
            pstmt.setString(2, newUser.getLastName());
            pstmt.setString(3, newUser.getUsername());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getPassword());


            int rowsInserted = pstmt.executeUpdate();
            //add the autogenerated values into the new user so they match the serial/generated one from the repository.
            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                newUser.setId(rs.getInt(1));

            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
            System.err.println("database error 3");
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
