package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.onetoone.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
