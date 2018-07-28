package com.hello.transaction;


import org.springframework.stereotype.Repository;

@Repository(value = "bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

    @Override
    public int findBookPriceByIsbn(String isbn) {
        System.out.println("找书");
        return 0;
    }

    @Override
    public void updateBookStock(String isbn) {
        System.out.println("更新书库存");
    }

    @Override
    public void updateUserAccount(String username, int price) {
        System.out.println("减去用户钱");
    }
}
