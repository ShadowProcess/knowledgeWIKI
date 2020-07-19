package com.example.springboottransaction.serviceImpl;

import com.example.springboottransaction.dao.BankAccountDao;
import com.example.springboottransaction.model.Account;
import com.example.springboottransaction.service.AccountService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Spring 业务实现类
 *
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private BankAccountDao bankAccountDao;

    @Transactional //jpa规范
    @Override
    public void transferAccounts(int fromUser, int toUser, float account) {
            Account fromAccount = bankAccountDao.getOne(fromUser);
            fromAccount.setBalance(fromAccount.getBalance()-account);

            bankAccountDao.save(fromAccount);

            Account toAccount = bankAccountDao.getOne(toUser);
            toAccount.setBalance(toAccount.getBalance()+account);

            bankAccountDao.save(toAccount);
    }
}
