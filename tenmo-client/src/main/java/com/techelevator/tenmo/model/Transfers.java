package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {


    private Long transfer_id;
    private Long transfer_type_id;
    private Long transfer_status_id;
    private Long accountFromId;
    private Long accountToId;
    private BigDecimal amount;
    private Account accountFrom;
    private Account accountTo;



    public Transfers() {
    }

    public Transfers(Long transfer_id, Long transfer_type_id, Long transfer_status_id, Long account_from, Long accountToId, BigDecimal amount, Account accountFrom, Account accountTo) {
        this.transfer_id = transfer_id;
        this.transfer_type_id = transfer_type_id;
        this.transfer_status_id = transfer_status_id;
        this.accountFromId = account_from;
        this.accountToId = accountToId;
        this.amount = amount;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    public Long getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(Long transfer_id) {
        this.transfer_id = transfer_id;
    }

    public Long getTransfer_type_id() {
        return transfer_type_id;
    }

    public void setTransfer_type_id(Long transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public Long getTransfer_status_id() {
        return transfer_status_id;
    }

    public void setTransfer_status_id(Long transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public Long getAccount_from() {
        return accountFromId;
    }

    public void setAccount_from(Long account_from) {
        this.accountFromId = account_from;
    }

    public Long getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(Long accountToId) {
        this.accountToId = accountToId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(Long accountFromId) {
        this.accountFromId = accountFromId;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }
}
