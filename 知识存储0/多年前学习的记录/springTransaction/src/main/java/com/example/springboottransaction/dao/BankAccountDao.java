package com.example.springboottransaction.dao;

import com.example.springboottransaction.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountDao extends JpaRepository<Account,Integer> {

    /**
     * 账户
     */

}
