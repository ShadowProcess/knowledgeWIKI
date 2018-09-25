package com.annotation.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

    @Autowired
    private ClassB classB;

    //原写法
//    public void printClass(){
//        System.out.println("this is class A "+this);
//        classB.printClass();
//    }

    public void printClass(){
        System.out.println("this is class A "+this);
        getClassB().printClass();
    }

    @Lookup
    public ClassB getClassB(){
        return null;
    }

}
