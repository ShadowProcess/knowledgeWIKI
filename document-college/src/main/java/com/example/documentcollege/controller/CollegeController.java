package com.example.documentcollege.controller;

import com.example.documentcollege.hook.BeanHook;
import com.example.documentcollege.service.ExcelService;
import com.example.documentcollege.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CollegeController {

    @Autowired
    ExcelService excelService;
    @Autowired
    PDFService pdfService;

    @GetMapping("/excel")
    public String excel() throws IOException {
        excelService.test();
        return "excel";
    }

    @GetMapping("/pdf")
    public String pdf(){
        pdfService.exportToResponse(BeanHook.self().getResponse());
        return "pdf";
    }
}
