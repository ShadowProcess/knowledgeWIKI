package com.atguigu.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.spring.hibernate.dao.BookShopDao;
import com.atguigu.spring.hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;
	
	/**
	 * Spring hibernate 事务的流程
	 * 1. 在方法开始之前
	 * ①. 获取 Session
	 * ②. 把 Session 和当前线程绑定, 这样就可以在 Dao 中使用 SessionFactory 的
	 * getCurrentSession() 方法来获取 Session 了
	 * ③. 开启事务
	 * 
	 * 2. 若方法正常结束, 即没有出现异常, 则
	 * ①. 提交事务
	 * ②. 使和当前线程绑定的 Session 解除绑定
	 * ③. 关闭 Session
	 * 
	 * 3. 若方法出现异常, 则:
	 * ①. 回滚事务
	 * ②. 使和当前线程绑定的 Session 解除绑定
	 * ③. 关闭 Session
	 */
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
	}

}
