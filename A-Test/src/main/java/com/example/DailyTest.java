package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DailyTest {

    @Test
    public void _0(){
        Object o = Optional.ofNullable(null).isPresent();
    }

    @Test
    public void _1(){
        boolean ss = StringUtils.equals("-1", "ss");
        System.out.println(ss);
    }

    @Test
    public void _2(){
        List<String> list = new ArrayList<>();

        boolean anyMatch = list.stream().anyMatch(it -> it.equals("1"));
        System.out.println(anyMatch);

        boolean noneMatch = list.stream().noneMatch(it -> it.equals("1"));
        System.out.println(noneMatch);

        boolean allMatch = list.stream().allMatch(it -> it.equals("1"));
        System.out.println(allMatch);
    }

    /**
     * <<< 无符号右移
     * <<  有符号右移
     *
     * 计算机表示数字正负不是用+ -加减号来表示，而是用最高位数字来表示，0表示正，1表示负
     *
     * 1.有符号右移>>（若正数,高位补0,负数,高位补1）
     *
     *
     * 正数:例如4>>2
     *
     * 首先写出4的二进制数，因为是正数所以最高位为0，也就是第一个
     *
     * 0000 0000 0000 0000 0000 0000 0000 0100
     *
     * 右移两位得到（高位补0）
     *
     * 000000 0000 0000 0000 0000 0000 0000 01
     *
     * 结果为：1，右移n位也就是4/（2^n）
     *
     *
     * 负数：例如-4>>2（高位补1）
     *
     * 首先写出-4的二进制数,因为是负数所以最高位为1
     *
     * 1000 0000 0000 0000 0000 0000 0000 0100
     *
     * 然后写出-4补码：保证符号位不变，其余位置全部取反，之后再加1（简便方法是：从右往左遇到第一个1，然后剩下的全部取反就是了）
     *
     * 1111 1111 1111 1111 1111 1111 1111 1100（补码）
     *
     * 右移2位： 在高位补1
     *
     * 1111 1111 1111 1111 1111 1111 1111 1111
     *
     * 根据补码写出原码才是我们所求的结果， 保留符号位，然后按位取反再加上1
     *
     * 100000 0000 0000 0000 0000 0000 0000 00（取反后的结果）
     *
     * 100000 0000 0000 0000 0000 0000 0000 01（再加1）
     *
     * 结果为：-1
     */

    /**
     * 无符号右移>>>(不论正负,高位均补0)
     *
     *
     * 正数：例如4>>>2
     *
     * 与4>>2的运算相同，结果也为1
     *
     *
     * 负数：例如-4>>>2
     *
     * 首先写出-4的二进制数,因为是负数所以最高位为1
     *
     * 1000 0000 0000 0000 0000 0000 0000 0100
     *
     * 然后写出-4补码：保证符号位不变，其余位置全部取反，之后再加1（简便方法是：从右往左遇到第一个1，然后剩下的全部取反就是了）
     *
     * 1111 1111 1111 1111 1111 1111 1111 1100（补码）
     *
     * 右移2位： 在高位补0
     *
     * 0011 1111 1111 1111 1111 1111 1111 1111
     *
     * 结果为：1073741823
     */
    @Test
    public void _4(){
        int i = -4 >>> 2;
        System.out.println(i);
    }


}
