package com.example.jpademo.jpa.repo;

import com.example.jpademo.jpa.entity.onetoone.Department;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends JpaRepository<Department,Integer>, JpaSpecificationExecutor<Department> {
}
