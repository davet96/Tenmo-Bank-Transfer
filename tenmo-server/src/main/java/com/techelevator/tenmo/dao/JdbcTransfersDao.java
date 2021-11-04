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
    public List<Transfers> getTransferHistory(Long transferId) {
        List<Transfers> transfers= new ArrayList<>();
        String sql = "Select * " +
                "From transfers" +
                "Join transfer_types ON transfer_type.transfer_type_id = transfers.transfer_type_id" +
                "Join account_id ON accounts.account_id = transfers.account_from" +
                "Join account_id ON accounts.account_id = transfers.account_to" +
                "Where transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        while (results.next()){
             Transfers newTransfers = mapRowTransfers(results);
             transfers.add(newTransfers);
        }

        return transfers;
    }

    @Override
    public void sendBucks(Account accountFrom, Account accountTo, BigDecimal amount){

        String sql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "Values (default, 2, 2, ?, ?, ?);";

       jdbcTemplate.update(sql,accountFrom.getAccount_id(), accountTo.getAccount_id(), amount);

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
