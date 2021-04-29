package com.example;

import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class Person {

    String name;
    String s;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", s='" + s + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Date from = Date.from(Instant.now().plus(30, DAYS));
        System.out.println(from);
        Person person = null;
        person.ss();//可以正常调用

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

    static void ss(){
        System.out.println("ss");
    }
}
