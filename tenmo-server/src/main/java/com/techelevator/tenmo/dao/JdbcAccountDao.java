package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

     jdbcTemplate.update(sql, amount, account.getUser_id());
    }

    @Override
    public void withdraw(Account account, BigDecimal amount){

        String sql = "UPDATE accounts " +
                "SET balance = balance - ? " +
                "WHERE user_id = ?; ";

        jdbcTemplate.update(sql, amount, account.getUser_id());
    }




    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccount_id(rs.getLong("account_id"));
        account.setUser_id(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setUsername(rs.getString("username"));
        return account;
    }
}
