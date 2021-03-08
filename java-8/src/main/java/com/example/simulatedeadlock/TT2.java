package com.example.simulatedeadlock;

import org.junit.Test;

public class TT2 implements TT {

    @Test
    public void test() {
        //访问接口中的静态变量
        System.out.println(TT.i);
    }
}
