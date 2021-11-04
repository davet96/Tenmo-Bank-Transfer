package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;
    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getUserAccount(String username) {
        Account account = null;
        String sql = "SELECT account_id, accounts.user_id, balance, users.username FROM accounts " +
                "JOIN users ON users.user_id = accounts.user_id WHERE users.username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if (results.next()) {
            account = mapRowToAccount(results);
        }
        return account;
    }

    @Override
    public void deposit(Account account, BigDecimal amount){

        String sql = "UPDATE accounts " +
                "SET balance = balance + ? " +
                "WHERE user_id = ?; ";

     jdbcTemplate.update(sql, amount, account.getUserId());
    }

    @Override
    public void withdraw(Account account, BigDecimal amount){
        String sql = "UPDATE accounts " +
                "SET balance = balance - ? " +
                "WHERE user_id = ?; ";

        jdbcTemplate.update(sql, amount, account.getUserId());
    }

    @Override
    public List<User> viewUserList(Long userId){
        List<User> viewUser = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash " +
                "FROM users " +
                "EXCEPT SELECT user_id, username, password_hash " +
                "FROM users " +
                "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
                while(results.next()){
                    viewUser.add(mapRowToUser(results));
                }
                return viewUser;
    }
    @Override
    public List<Account> getAccountWithUsername(){
        List<Account> accountList = new ArrayList<>();
        String sql = "Select account_id, balance, accounts.user_id, username " +
                "From accounts " +
                "JOIN users ON accounts.user_id = users.user_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                Account account = mapRowToAccount(results);
                accountList.add(account);
            }
            return accountList;
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getLong("account_id"));
        account.setUserId(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setUsername(rs.getString("username"));
        return account;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }
}
