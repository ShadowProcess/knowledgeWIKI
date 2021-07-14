package com.example.javaspi;

import com.example.javaspi.service.SpiTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ServiceLoader;

@SpringBootApplication
public class JavaSpiApplication {

    public static void main(String[] args) {

        /**
         * Java Spi 机制
         */
        ServiceLoader<SpiTest> serviceLoader = ServiceLoader.load(SpiTest.class);
        for (SpiTest spiTest : serviceLoader) {
            spiTest.hello();
        }

        SpringApplication.run(JavaSpiApplication.class, args);
    }

}
