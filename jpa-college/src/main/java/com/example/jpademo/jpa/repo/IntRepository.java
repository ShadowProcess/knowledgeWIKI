package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.integer_type.IntegerDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntRepository extends JpaRepository<IntegerDemo,Integer> {
}
