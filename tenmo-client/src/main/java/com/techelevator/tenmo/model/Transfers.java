package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {


    private Long transferId;
    private Long transferTypeId;
    private Long transferStatusId;
    private Long accountFromId;
    private Long accountToId;
    private BigDecimal amount;
    private Account accountFrom;
    private Account accountTo;


    public Transfers() {
    }

    public Transfers(Long transferId, Long transferTypeId, Long transferStatusId, Long accountFromId, Long accountToId, BigDecimal amount, Account accountFrom, Account accountTo) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFromId = accountFromId;
        this.accountToId = accountToId;
        this.amount = amount;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Long getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(Long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public Long getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(Long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public Long getAccount_from() {
        return accountFromId;
    }
    public Long getAccount_to() {
        return accountToId;
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
