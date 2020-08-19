package com.example.jpademo.controller;

import com.example.jpademo.jpa.entity.onetoone.Department;
import com.example.jpademo.jpa.entity.onetoone.Manager;
import com.example.jpademo.jpa.repo.DepartmentRepository;
import com.example.jpademo.jpa.repo.ManagerRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.util.Optional;

@Controller
public class OneToOneController {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("add1")
    @ResponseBody
    public String add(){
        Department department = new Department();
        department.setDeptName("路径");

        Manager mgr = new Manager();
        mgr.setMgrName("哈哈");

        department.setMgr(mgr);
        managerRepository.save(mgr);
        departmentRepository.save(department);
        return "ok";
    }


    @GetMapping("query1")
    @ResponseBody
    public Department q(){
        Specification sp = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                Path id = root.get("id");
                Path mgr = root.get("mgr").get("id");
                Predicate p = criteriaBuilder.equal(id, "6");
                Predicate p1 = criteriaBuilder.equal(mgr, "5");
                return criteriaBuilder.and(p,p1);
            }
        };
        Optional<Department> one = departmentRepository.findOne(sp);
        return one.orElse(null);
    }

}
