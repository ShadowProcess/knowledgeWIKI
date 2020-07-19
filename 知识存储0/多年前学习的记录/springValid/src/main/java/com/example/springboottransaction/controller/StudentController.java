package com.example.springboottransaction.controller;


import com.example.springboottransaction.model.Student;
import com.example.springboottransaction.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/add")
    public String addStudent(@Valid Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        } else {
            studentService.add(student);
        }
        return "添加成功!";
    }

    //返回到指定页面
    @RequestMapping("/index")
    public ModelAndView getIndex(){
        return new ModelAndView("studentDemo");
    }

}
