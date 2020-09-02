package com.example.spi_college.spi;

public class SpiServiceA implements SpiService {

    @Override
    public void hello() {
        System.out.println("spi service A");
    }
}
