package com.execution_engine.java;

/**
 * 测试解释器模式和JIT编译模式
 *  -Xint   : 6520ms  //仅仅开启解释器模式
 *  -Xcomp  : 950ms   //仅仅开启编译模式
 *  -Xmixed : 936ms   //开启二者混合模式
 */
public class IntCompTest {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        testPrimeNumber(1000000);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));
    }

    public static void testPrimeNumber(int count){
        for (int i = 0; i < count; i++) {
            //计算100以内的质数
            label:for(int j = 2;j <= 100;j++){
                for(int k = 2;k <= Math.sqrt(j);k++){
                    if(j % k == 0){
                        continue label;
                    }
                }
                //System.out.println(j);
            }

        }
    }
}
