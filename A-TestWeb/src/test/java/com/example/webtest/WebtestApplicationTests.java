package com.example.webtest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
class WebtestApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void ss(){
        System.out.println(nxb_valid());
    }


    public boolean nxb_valid() {
        try {
//            Date now = new Date();
//            String dateStr = new SimpleDateFormat("yyyyMMddHHmm").format(now);
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
            if (dateStr.compareTo("202002121130") >= 0 && dateStr.compareTo("202012312359") <= 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
