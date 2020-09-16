package com.example.jpademo.controller;

import com.example.jpademo.jpa.entity.integer_type.IntegerDemo;
import com.example.jpademo.jpa.repo.IntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class IntController {

    @Autowired
    private IntRepository intRepository;

    @GetMapping("add")
    @ResponseBody
    public String add() {
        Stream.of(1, 2).forEach(
                x -> {
                    IntegerDemo integerDemo = new IntegerDemo();
                    integerDemo.setA(1);
                    integerDemo.setB(1);
                    integerDemo.setC(1);
                    integerDemo.setE(1);
                    intRepository.save(integerDemo);
                }
        );
        return "ok";
    }

    @GetMapping("find")
    @ResponseBody
    public List<IntegerDemo> find() {
        List<IntegerDemo> all = intRepository.findAll();
        return all;
    }
}
