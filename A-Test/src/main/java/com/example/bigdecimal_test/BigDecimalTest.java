package com.example.bigdecimal_test;


import org.junit.Test;

import java.math.BigDecimal;

/**
 * Java和C语言都一样：
 * 小数默认是double类型；
 * 如果想要float类型，需要声明加f
 *
 * 不是所有小数都不能精确保存，有些是可以的 例如：0.5 0.125
 */

public class BigDecimalTest {

    @Test
    public void binary() {
        String s = Integer.toBinaryString(8);
        System.out.println(s);
    }

    @Test
    public void ss() {
        float a = 0.1f;
        float b = 0.2f;
        System.out.println(a + b == 0.3);    //false
        System.out.println(a + b == 0.3f);   //true
        System.out.println(0.1 + 0.2 == 0.3);//false
        System.out.println(0.1d + 0.2d == 0.3); //false
        System.out.println(0.1d + 0.2d == 0.3d);//false
        System.out.println(0.5 + 0.5 == 1.0);   //true
        /**
         * 4、为什么 0.1f+0.2f == 0.3f 成立
         * 上面分析的是0.1+0.2都是浮点数表达，即0.1f+0.2f，结果是3E99 999A，直接用浮点数表达0.3f，
         * 也会得到结果3E99 999A，所以Java代码中第一个双等号的结果是true。
         * 因此，这个 true 的产生是二者截断后恰好都有进位的原因，并不是精确意义上的相等。
         *
         * 5、为什么0.1+0.2 == 0.3 不成立
         *
         * 用同样的方式分析double，给double型变量直接赋值 0.3 和赋值 0.1+0.2 会得到不一样的结果，
         * 具体原因是 0.1 + 0.2的时候尾数截断产生了进位，而直接表达 0.3 的时候没有，详细流程可以自己分析一下，这里不赘述。
         * ————————————————
         */
    }


    @Test
    public void fd() {
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
     * <p>
     * BigDecimal一定不会丢失精度吗？
     * 1.何使用非String构造器，和float和double一样，会丢失精度
     * 2.如果使用String构造器，那么不会丢失精度，可以保证计算完全正确
     */
    @Test
    public void t() {
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
