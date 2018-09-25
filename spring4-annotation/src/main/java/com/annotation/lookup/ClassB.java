package com.annotation.lookup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "prototype") ////注意这里，classB务必一直都得有这个多例prototype标签
public class ClassB {
    public void printClass() {
        System.out.println("this is Class B " + this);
    }
}
