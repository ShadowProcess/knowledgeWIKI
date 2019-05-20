package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.onetomany.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
