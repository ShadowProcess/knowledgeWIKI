package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.onetoone.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
}
