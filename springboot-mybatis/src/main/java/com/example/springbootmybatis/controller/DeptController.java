package com.example.springbootmybatis.controller;

import com.example.springbootmybatis.bean.Department;
import com.example.springbootmybatis.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable Integer id){
        return departmentMapper.getDeptById(id);
    }


    @GetMapping("/dept")
    public Department insert(Department department){
        departmentMapper.insertDept(department);
        return department;
    }
}
