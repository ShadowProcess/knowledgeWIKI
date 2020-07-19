package com.example.springboottransaction.service;

public interface AccountService {

    //转账
    public void transferAccounts(int fromUser, int toUser, float account);
}
