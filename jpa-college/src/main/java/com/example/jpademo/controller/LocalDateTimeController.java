package com.example.jpademo.controller;

import com.example.jpademo.jpa.entity.local_date_time.LocalDateTimeCollege;
import com.example.jpademo.jpa.repo.LocalDateTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.*;
import java.util.List;

@Controller
public class LocalDateTimeController {

    @Autowired
    private LocalDateTimeRepository repository;

    @GetMapping(value = "save")
    @ResponseBody
    public String save(){
        LocalDateTimeCollege college = new LocalDateTimeCollege();
        college.setLocalDate(LocalDate.now());
        college.setLocalDateTime(LocalDateTime.now());
        college.setLocalTime(LocalTime.now());
        college.setM(Month.APRIL);
        college.setYm(YearMonth.now());
        college.setY(Year.now());
        repository.save(college);
        return "ok";
    }

    @GetMapping(value = "query")
    @ResponseBody
    public List<LocalDateTimeCollege> query(){
        List<LocalDateTimeCollege> all = repository.findAll();
        return all;
    }
}
