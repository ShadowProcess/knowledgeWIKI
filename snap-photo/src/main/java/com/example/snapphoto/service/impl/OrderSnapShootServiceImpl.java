package com.example.snapphoto.service.impl;

import com.example.snapphoto.service.OrderSnapShootService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OrderSnapShootServiceImpl implements OrderSnapShootService {
    @Override
    public void exec(String jsLocate, String uri, String dir) {
        try {
            String bashCommand = "phantomjs" +
                    " " +
                    jsLocate +
                    " " +
                    "\"" +
                    uri +
                    "\"" +
                    " " +
                    dir +
                    " ";
            log.info("bash command:{}", bashCommand);
            Process process = Runtime.getRuntime().exec(bashCommand);
            log.info("1");
            process.waitFor(5, TimeUnit.SECONDS);
            log.info("2");
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            log.info("3");
            LineNumberReader input = new LineNumberReader(ir);
            log.info("4");
            String line;
            while ((line = input.readLine()) != null) {
                log.info("Console:{}", line);
            }
            log.info("exec complete");
        } catch (Exception e) {
            log.error("ProcessCommand:", e);
        }
    }

    @Override
    public String getImgDir() {
        val now = LocalDate.now().toString();
        return "/tmp/" + now + "/" + getImgName();
    }

    public String getImgName() {
        SecureRandom secureRandom = new SecureRandom();
        int i = secureRandom.nextInt(1000);
        return i + ".jpg";
    }
}
