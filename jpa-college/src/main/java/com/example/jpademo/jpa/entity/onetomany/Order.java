package com.example.jpademo.jpa.entity.onetomany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "jpa_orders")
@Entity
public class Order implements Serializable {

    private Integer id;
    private String orderName;
    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "order_name")
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    //映射单向n-1的关系
    //使用@ManyToOne来映射多对一的关联关系
    //使用@JoinColumn来映射外键
    //可以使用@ManyToOne的fetch属性来修改默认的关联属性的加载策略
    @JoinColumn(name = "CUSTOMER_ID")
    @ManyToOne(fetch = FetchType.LAZY) //懒加载
    @NotFound(action = NotFoundAction.IGNORE)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", customer=" + customer +
                '}';
    }
}
