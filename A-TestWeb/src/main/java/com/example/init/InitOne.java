package com.example.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitOne implements CommandLineRunner {


    /**
     * 参数可以通过如下方式传入：
     * <p>
     * java -jar MyProject.jar 参数1 参数2 参数3
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command Line Runner !!!");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
