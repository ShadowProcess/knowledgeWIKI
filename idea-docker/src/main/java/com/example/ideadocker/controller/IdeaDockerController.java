package com.example.ideadocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdeaDockerController {


    @GetMapping(value = "/123")
    public String s(){
        return "build docker success";
    }
}
