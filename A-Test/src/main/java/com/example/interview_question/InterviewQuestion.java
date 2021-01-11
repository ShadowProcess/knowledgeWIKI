package com.example.interview_question;

import org.junit.Test;

public class InterviewQuestion {


    @Test
    public void instanceOf() {
        Fu f1 = new Zi();
        Fu f2 = new Son();

        if (f1 instanceof Zi) { // true
            System.out.println("f1是Zi的类型");
        } else {
            System.out.println("f1是Son的类型");
        }

    }


    @Test
    public void polymorphic() {
        /**
         * Java多态：
         *
         * 1.多态成员变量：编译运行都看左边
         *      Fu f=new Zi();
         *      System.out.println(f.num);//f是Fu中的值，只能取到父中的值
         *
         * 2.多态成员方法：编译看左边，运行看右边
         *      Fu f1=new Zi();
         *      System.out.println(f1.show());//f1的门面类型是Fu,但实际类型是Zi,所以调用的是重写后的方法
         *
         *
         */
        Fu fu = new Zi();
        System.out.println(fu.s); //fu
        fu.say(); //Zi
    }


    static class Fu {
        private final String s = "fu";

        public void say() {
            System.out.println(s);
        }
    }

    static class Zi extends Fu {
        private final String s = "Zi";

        public void say() {
            System.out.println(s);
        }
    }

    static class Son extends Fu {
        private final String s = "Son";

        public void say() {
            System.out.println(s);
        }
    }
}
