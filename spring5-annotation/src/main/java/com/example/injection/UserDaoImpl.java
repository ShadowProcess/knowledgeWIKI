package com.example.injection;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("save");
    }
}
