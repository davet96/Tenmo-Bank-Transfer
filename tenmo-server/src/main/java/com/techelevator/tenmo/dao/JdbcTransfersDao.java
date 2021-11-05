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
    public void sendBucks(Account accountFrom, Account accountTo, BigDecimal amount){

            String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "Values (default, 2, 2, ?, ?, ?);";
            jdbcTemplate.update(sql, accountFrom.getAccountId(), accountTo.getAccountId(), amount);

            accountDao.deposit(accountTo, amount);
            accountDao.withdraw(accountFrom, amount);

    }

    @Override
    public List<Transfers> getAllTransfers(int userId) {
        List<Transfers> list = new ArrayList<>();
        String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo FROM transfers t " +
                "JOIN accounts a ON t.account_from = a.account_id " +
                "JOIN accounts b ON t.account_to = b.account_id " +
                "JOIN users u ON a.user_id = u.user_id " +
                "JOIN users v ON b.user_id = v.user_id " +
                "WHERE a.user_id = ? OR b.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (results.next() ) {
            Transfers transfer = mapRowTransfers(results);
            list.add(transfer);
        }
        return list;
    }

    @Override
    public Transfers getTransferById(int transactionId) {
        Transfers transfer = new Transfers();
        String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo, ts.transfer_status_desc, tt.transfer_type_desc FROM transfers t " +
                "JOIN accounts a ON t.account_from = a.account_id " +
                "JOIN accounts b ON t.account_to = b.account_id " +
                "JOIN users u ON a.user_id = u.user_id " +
                "JOIN users v ON b.user_id = v.user_id " +
                "JOIN transfer_statuses ts ON t.transfer_status_id = ts.transfer_status_id " +
                "JOIN transfer_types tt ON t.transfer_type_id = tt.transfer_type_id " +
                "WHERE t.transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);
        if (results.next()) {
            transfer = mapRowTransfers(results);
        }
        return transfer;
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
