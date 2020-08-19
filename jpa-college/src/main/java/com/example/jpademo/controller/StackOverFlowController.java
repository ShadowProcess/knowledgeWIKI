package com.example.jpademo.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 两个互相引用的对象，在序列化为json时
 * java.lang.StackOverflowError: null
 * A类的getS1方法 ...
 * A类的getS2方法 ...
 * A类的getB方法 ...
 * B类的getS1方法 ...
 * B类的getS2方法 ...
 * B类的getA方法 ...
 * A类的getS1方法 ...
 * A类的getS2方法 ...
 * A类的getB方法 ...
 * B类的getS1方法 ...
 * B类的getS2方法 ...
 * B类的getA方法 ...
 * A类的getS1方法 ...
 * A类的getS2方法 ...
 * A类的getB方法 ...
 * B类的getS1方法 ...
 * B类的getS2方法 ...
 * B类的getA方法 ...
 * A类的getS1方法 ...
 * A类的getS2方法 ...
 * A类的getB方法 ...
 * B类的getS1方法 ...
 * B类的getS2方法 ...
 * B类的getA方法 ...
 * ...
 * ...
 * ...
 */

@Controller
public class StackOverFlowController {

    @GetMapping("json")
    @ResponseBody
    public A json() {
        A a = new A();
        a.setS1("a");
        a.setS2("b");

        B b = new B();
        b.setA(a);
        b.setS1("z");
        b.setS2("x");

        a.setB(b);
        return a;
    }

    public class A {
        String s1;
        String s2;
        B b;

        public String getS1() {
            System.out.println("A类的getS1方法 ...");
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            System.out.println("A类的getS2方法 ...");
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        public B getB() {
            System.out.println("A类的getB方法 ...");
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        @Override
        public String toString() {
            System.out.println("序列化不会调用");
            return "A{" +
                    "s1='" + s1 + '\'' +
                    ", s2='" + s2 + '\'' +
                    ", b=" + b +
                    '}';
        }
    }

    public class B {
        String s1;
        String s2;
        //@JsonIgnore
        A a;

        public String getS1() {
            System.out.println("B类的getS1方法 ...");
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            System.out.println("B类的getS2方法 ...");
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        public A getA() {
            System.out.println("B类的getA方法 ...");
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        @Override
        public String toString() {
            System.out.println("序列化不会调用");
            return "B{" +
                    "s1='" + s1 + '\'' +
                    ", s2='" + s2 + '\'' +
                    ", a=" + a +
                    '}';
        }
    }
}
