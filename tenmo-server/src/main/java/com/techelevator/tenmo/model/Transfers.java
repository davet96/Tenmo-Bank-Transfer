package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {
    private Long transferId;
    private Long transferTypeId;
    private Long transferStatusId;
    private Account accountFrom;
    private Account accountTo;
    private BigDecimal amount;
    private Long accountToId;
    private Long accountFromId;


    public Transfers() {
    }

    public Transfers(Long transferId, Long transferTypeId, Long transferStatusId, Account accountFrom, Account accountTo, BigDecimal amount, Long accountToId, Long accountFromId) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.accountFromId = accountFromId;
        this.accountToId = accountToId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(Long accountToId) {
        this.accountToId = accountToId;
    }

    public Long getAccountFromId() {
        return accountFromId;
    }

    public void setAccountFromId(Long accountFromId) {
        this.accountFromId = accountFromId;
    }
}