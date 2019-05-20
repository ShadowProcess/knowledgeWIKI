package com.example.jpademo.jpa.entity.oldmodelstudy;

import com.example.jpademo.jpa.entity.onetomany.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //1.创建EntityManagerFactory
        String persistenceUnitName = ""; //老式框架中 persistence.xml配置的名字

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql",false); //关闭SQL语句打印

        EntityManagerFactory entityManagerFactory =
                //Persistence.createEntityManagerFactory(persistenceUnitName);
                Persistence.createEntityManagerFactory(persistenceUnitName,properties);

        //2.创建EntityManager 。 类似于hibernate的SessionFactory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //3.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //4.进行持久化操作
        Customer customer = new Customer();
        customer.setAge(12);
        customer.setEmail("jia@163.com");
        customer.setLastName("Tom");
        customer.setCreatedTime(new Date());
        customer.setBirth(new Date());

        entityManager.persist(customer);

        //5.提交事务
        transaction.commit();

        //6.关闭EntityManager
        entityManager.close();

        //7.关闭EntityManagerFactory
        entityManagerFactory.close();
    }
}
