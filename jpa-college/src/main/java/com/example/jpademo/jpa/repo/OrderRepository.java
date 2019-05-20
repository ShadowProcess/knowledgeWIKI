package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.onetomany.Order;
import com.example.jpademo.jpa.support.StandardRepository;

public interface OrderRepository extends StandardRepository<Order,Integer> {
}
