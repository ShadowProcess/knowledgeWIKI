package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.integer_type.IntegerDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IntRepository extends JpaRepository<IntegerDemo,Integer>, JpaSpecificationExecutor<IntegerDemo> {
}
