package com.revature.project0.repo;

import com.revature.project0.models.Account;
import com.revature.project0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountRepository {

    public AccountRepository() {
        System.out.println("Instantiating " + this.getClass().getName());
    }

    public Optional<Account> findAccountById(double balance) {
        Optional<Account> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT account_balance FROM project_0.account a " +
                         "WHERE account_id = ? ";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, balance);


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _user;
    }

    public void save(Account newBalance) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project_0.account(account_balance) " +
                         "VALUES(?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, newBalance.getBalance());

            int dataInserted = pstmt.executeUpdate();

            if (dataInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                newBalance.setBalance(rs.getDouble(1));
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
