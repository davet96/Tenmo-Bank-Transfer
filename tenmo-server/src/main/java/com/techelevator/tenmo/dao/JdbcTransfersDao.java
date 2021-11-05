package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransfersDao implements TransfersDao{

    private AccountDao accountDao;

    private JdbcTemplate jdbcTemplate;
    public JdbcTransfersDao(JdbcTemplate jdbcTemplate, AccountDao accountDao){
        this.jdbcTemplate = jdbcTemplate;
        this.accountDao = accountDao;
    }

    @Override
    public List<Transfers> getTransferHistory(String username) {
        List<Transfers> transfers = new ArrayList<>();
        String sql = "SELECT t.transfer_id, t.transfer_type_id, t.transfer_status_id, t.account_from, t.account_to, t.amount " +
                "FROM transfers t " +
                "JOIN accounts a ON t.account_from = a.account_id " +
                "JOIN users u ON a.user_id = u.user_id " +
                "WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,username);
        if(results.next()) {
            Transfers newTransfers = mapRowTransfers(results);
            transfers.add(newTransfers);
        }

        return transfers;

    }
    @Override
    public void sendBucks(Account accountFrom, Account accountTo, BigDecimal amount){

            String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "Values (default, 2, 2, ?, ?, ?);";
            jdbcTemplate.update(sql, accountFrom.getAccountId(), accountTo.getAccountId(), amount);

            accountDao.deposit(accountTo, amount);
            accountDao.withdraw(accountFrom, amount);

    }



    private Transfers mapRowTransfers(SqlRowSet rs){
        Transfers transfers = new Transfers();
       transfers.setTransferId(rs.getLong("transfer_id"));
       transfers.setTransferTypeId(rs.getLong("transfer_type_id"));
       transfers.setTransferStatusId(rs.getLong("transfer_status_id"));
       transfers.setAccountFromId(rs.getLong("account_from"));
       transfers.setAccountToId(rs.getLong("account_to"));
       transfers.setAmount(rs.getBigDecimal("amount"));

       return transfers;
    }




}
