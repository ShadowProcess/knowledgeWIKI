package com.java8.multi;

public class Application {

    public static void testMultiJar()
    {
        Generator localGenerator = new Generator();
        System.out.println("Generated strings: " + localGenerator.createStrings());
    }
}
