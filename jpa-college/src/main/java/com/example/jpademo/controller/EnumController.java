package com.example.jpademo.controller;

import com.example.jpademo.jpa.entity.enum_type.AuditState;
import com.example.jpademo.jpa.entity.enum_type.EnumTable;
import com.example.jpademo.jpa.repo.EnumRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EnumController {


    @Autowired
    private EnumRepository enumRepository;

    @GetMapping("/enum")
    @ResponseBody
    public String s(){
        EnumTable enumTable0 = new EnumTable();
        enumTable0.setAuditState(AuditState.A);
        enumRepository.save(enumTable0);

        EnumTable enumTable1 = new EnumTable();
        enumTable1.setAuditState(AuditState.B);
        enumRepository.save(enumTable1);

        EnumTable enumTable2 = new EnumTable();
        enumTable2.setAuditState(AuditState.C);
        enumRepository.save(enumTable2);

        EnumTable enumTable3 = new EnumTable();
        enumTable3.setAuditState(AuditState.D);
        enumRepository.save(enumTable3);
        return "s";
    }
}
