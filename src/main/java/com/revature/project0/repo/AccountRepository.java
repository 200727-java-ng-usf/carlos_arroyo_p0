package com.revature.project0.repo;

import com.revature.project0.models.Account;
import com.revature.project0.models.User;
import com.revature.project0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.project0.AppDriver.app;

public class AccountRepository {

    public AccountRepository() {
//        System.out.println("Instantiating " + this.getClass().getName());
    }

    public Optional<Account> findAccountById(int id) {
        Optional<Account> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM project_0.account a " +
                         "WHERE account_id = ? ";


            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();


            _account = mapResultSet(rs).stream().findFirst();


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return _account;
    }


    public void save(Account newAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project_0.account (account_id, account_balance, customer_id) " +
                         "VALUES(?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
//            int newNum = (Integer) app.getCurrentAccount().getBalance();
            pstmt.setInt(1, newAccount.getId());
            pstmt.setDouble(2, newAccount.getBalance());
            pstmt.setInt(3, newAccount.getId());

            pstmt.executeUpdate();

            int dataInserted = pstmt.executeUpdate();

            double currentBalance = app.getCurrentAccount().getBalance();
//            if (dataInserted != 0) {
//                ResultSet rs = pstmt.getGeneratedKeys();
//
//                rs.next();
////                newBalance.setBalance(rs.getDouble(1));
//            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    public void update(double  updateBalance) {
//        double currentBalance = app.getCurrentAccount().getBalance();
//        System.out.println(currentBalance);
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project_0.account " +
                         "SET account_balance = ? " +
                         "WHERE customer_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, updateBalance);
            pstmt.setInt(2, app.getCurrentAccount().getId());


            pstmt.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    private Set<Account> mapResultSet(ResultSet rs) throws SQLException {

        Set<Account> account = new HashSet<>();

        while (rs.next()) {
            Account temp = new Account();
            temp.setId(rs.getInt("account_id"));
            temp.setBalance(rs.getDouble("account_balance"));
            temp.setWithdraw(rs.getDouble("account_balance"));
            temp.setDeposit(rs.getDouble("account_balance"));

        }

        return account;
    }
}
