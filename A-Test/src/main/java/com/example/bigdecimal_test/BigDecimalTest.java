package com.example.bigdecimal_test;


import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void fd(){
        System.out.println(0.05 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);
        /**
         * 0.060000000000000005
         * 0.5800000000000001
         * 401.49999999999994
         * 1.2329999999999999
         */
        /**
         * 可以看到在Java中进行浮点数运算的时候，会出现丢失精度的问题。
         * 那么我们如果在进行商品价格计算的时候，就会出现问题。
         * 很有可能造成我们手中有0.06元，却无法购买一个0.05元和一个0.01元的商品。因为如上所示，
         * 他们两个的总和为0.060000000000000005。这无疑是一个很严重的问题，尤其是当电商网站的并发量上去的时候，
         * 出现的问题将是巨大的。可能会导致无法下单，或者对账出现问题。所以接下来我们就可以使用Java中的BigDecimal类来解决这类问题。
         *
         * 普及一下：
         * Java中float的精度为6-7位有效数字。double的精度为15-16位。
         */
    }




    /**
     * BigDecimal精度也丢失
     * 我们在使用BigDecimal时，使用它的BigDecimal(String)构造器创建对象才有意义。
     * 其他的如BigDecimal b = new BigDecimal(1)这种，还是会发生精度丢失的问题。
     *
     * BigDecimal一定不会丢失精度吗？
     * 1.何使用非String构造器，和float和double一样，会丢失精度
     * 2.如果使用String构造器，那么不会丢失精度，可以保证计算完全正确
     */
    @Test
    public void t(){
        BigDecimal a = new BigDecimal(1.01);
        BigDecimal b = new BigDecimal(1.02);
        BigDecimal c = new BigDecimal("1.01");
        BigDecimal d = new BigDecimal("1.02");
        System.out.println(a.add(b));
        System.out.println(c.add(d));
        /**
         * 2.0300000000000000266453525910037569701671600341796875
         * 2.03
         *
         * 总结：
         * 可见论丢失精度BigDecimal显的更为过分。
         * 但是使用BigDecimal的BigDecimal(String)构造器的变量在进行运算的时候却没有出现这种问题。
         */
    }
}
