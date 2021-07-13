package com.example.documentcollege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.ByteBuffer;
import java.util.LinkedList;

@SpringBootApplication
public class DocumentCollegeApplication {

    public static void main(String[] args) {
        //ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
        SpringApplication.run(DocumentCollegeApplication.class, args);
    }

}
