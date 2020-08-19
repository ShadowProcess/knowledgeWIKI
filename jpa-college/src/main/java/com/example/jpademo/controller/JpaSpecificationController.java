package com.example.jpademo.controller;

import com.example.jpademo.jpa.entity.integer_type.IntegerDemo;
import com.example.jpademo.jpa.entity.manytomany.Category;
import com.example.jpademo.jpa.repo.IntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
 * root    ：Root接口，代表查询的根对象，可以通过root获取实体中的属性
 * query   ：代表一个顶层查询对象，用来自定义查询
 * cb      ：用来构建查询，此对象里有很多条件方法
 *
 * public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);
 **/

@Controller
public class JpaSpecificationController {

    @Autowired
    private IntRepository intRepository;


    @GetMapping("/1")
    @ResponseBody
    public IntegerDemo testEqual() {
        Optional<IntegerDemo> one = intRepository.findOne((Specification<IntegerDemo>) (root, query, cb) -> {
            Path<Object> id = root.get("id"); //查询的是属性名，不是表的字段名
            return cb.equal(id, 3);
        });
        return one.orElseGet(null);
    }

    @GetMapping("/2")
    @ResponseBody
    public IntegerDemo testAnd() {
        Optional<IntegerDemo> one = intRepository.findOne((Specification<IntegerDemo>) (root, query, criteriaBuilder) -> {
            Path<Object> id = root.get("id"); //查询的是属性名，不是表的字段名
            Path<Object> a = root.get("a");
            Predicate p1 = criteriaBuilder.equal(id, 3);
            Predicate p2 = criteriaBuilder.equal(a, 1);
            return query.where(p1, p2).getRestriction();
        });
        return one.orElse(null);
    }


    @GetMapping("/3")
    @ResponseBody
    public List<IntegerDemo> testLike() {
        List<IntegerDemo> a1 = intRepository.findAll((Specification<IntegerDemo>) (root, query, cb) -> {
            Path<Object> a = root.get("a");
            Predicate like = cb.like(a.as(String.class), "1%");
            return query.where(like).getRestriction();
        });
        return a1;
    }


    @GetMapping("/4")
    @ResponseBody
    public List<IntegerDemo> testSort() {
        Specification<IntegerDemo> s = (root, query, cb) -> {
            Path<Object> a = root.get("a");
            Predicate like = cb.like(a.as(String.class), "1%");
            return query.where(like).getRestriction();
        };
        return intRepository.findAll(s, Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/5")
    @ResponseBody
    public Page<IntegerDemo> testPage() {
        Specification<IntegerDemo> s = (root, query, cb) -> {
            Path<Object> a = root.get("a");
            Predicate like = cb.like(a.as(String.class), "1%");
            return query.where(like).getRestriction();
        };
        Page<IntegerDemo> page = intRepository.findAll(s, PageRequest.of(0, 2));
        System.out.println(page.getContent());      //得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getTotalPages());   //得到总页数
        return page;
    }


    @GetMapping("/6")
    @ResponseBody
    public List<IntegerDemo> testIn() {
        Specification<IntegerDemo> s = (root, query, cb) -> {
            Path<Object> a = root.get("id");
            CriteriaBuilder.In<Object> in = cb.in(a);
            for (Integer i : Arrays.asList(3, 4, 5)) {
                in.value(i);
            }
            return query.where(in).getRestriction();
        };
        return intRepository.findAll(s);
    }


    @GetMapping("/7")
    @ResponseBody
    public List<IntegerDemo> testCriteriaQuery() {
        Specification<IntegerDemo> s = (root, query, cb) -> {
            Path<Object> id = root.get("id");
            Path<Object> a = root.get("a");
            Predicate p1 = cb.equal(id, 3);
            Predicate p2 = cb.equal(a, 1);
            return cb.and(p1, p2);
        };
        return intRepository.findAll(s);
    }

    @GetMapping("/8")
    @ResponseBody
    public List<IntegerDemo> testMultiSpec() {
        Specification<IntegerDemo> s1 = (root, query, cb) -> {
            Path<Object> id = root.get("id");
            Path<Object> a = root.get("a");
            Predicate p1 = cb.equal(id, 3);
            Predicate p2 = cb.equal(a, 1);
            return cb.or(p1, p2);
        };
        Specification<IntegerDemo> s2 = (root, query, cb) -> {
            Path<Object> id = root.get("id");
            Path<Object> a = root.get("a");
            Predicate p1 = cb.equal(id, 4);
            Predicate p2 = cb.equal(a, 1);
            return cb.or(p1, p2);
        };
        return intRepository.findAll(Specification.where(s1).and(s2));
    }


}
