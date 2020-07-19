package com.example.springboottransaction.dao;

import com.example.springboottransaction.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,Integer> {

}
