package com.example.numberutil;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

public class NumberUtilsTest {

    @Test
    public void _0() {
        String str = "12.7";

        //判断字符串是否为整数
        boolean digits = NumberUtils.isDigits(str);

        // 判断字符串是否为数字
        boolean number = NumberUtils.isNumber(str);

        //获取最大值,支持数组
        int max = NumberUtils.max(new int[]{1, 2, 3});

        // 获取参数中最小的值,支持传入数组
        int min = NumberUtils.min(10, 20, 30);

        // 将字符串转换为int类型,支持float,long,short等数值类型
        int i = NumberUtils.toInt(str);

        // 通过字符串创建BigDecimal类型 ,支持int,float,long等数值
        NumberUtils.createBigDecimal(str);


        /*
         * RandomUtils帮助我们产生随机数,不止是数字类型 ,
         * 连boolean类型都可以通过RandomUtils产生
         */
        RandomUtils.nextBoolean();
        RandomUtils.nextDouble();
        RandomUtils.nextLong();
        // 注意这里传入的参数不是随机种子,而是在0~1000之间产生一位随机数
        RandomUtils.nextInt(0, 1000);

    }
}
