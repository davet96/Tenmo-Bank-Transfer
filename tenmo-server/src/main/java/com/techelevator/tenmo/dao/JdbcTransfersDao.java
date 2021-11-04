package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Transfers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransfersDao implements TransfersDao{

    private JdbcTemplate jdbcTemplate;
    public JdbcTransfersDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfers> getTransferHistory(Long transfer_id) {
        List<Transfers> transfers= new ArrayList<>();
        String sql = "Select * " +
                "From transfers" +
                "Join transfer_types ON transfer_type.transfer_type_id = transfers.transfer_type_id" +
                "Join account_id ON accounts.account_id = transfers.account_from" +
                "Join account_id ON accounts.account_id = transfers.account_to" +
                "Where transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transfer_id);
        while (results.next()){
             Transfers newTransfers = mapRowTransfers(results);
             transfers.add(newTransfers);
        }

        return transfers;
    }

    private Transfers mapRowTransfers(SqlRowSet rs){
        Transfers transfers = new Transfers();
       transfers.setTransfer_id(rs.getLong("transfer_id"));
       transfers.setTransfer_type_id(rs.getLong("transfer_type_id"));
       transfers.setTransfer_status_id(rs.getLong("transfer_status_id"));
       transfers.setAccount_from(rs.getLong("account_from"));
       transfers.setAccount_to(rs.getLong("account_to"));
       transfers.setAmount(rs.getBigDecimal("amount"));
       return transfers;
    }




}
