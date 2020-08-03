package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DailyTest {


    /**
     * JAVA方法中的参数用final来修饰的效果
     * 很多人都说在JAVA中用final来修饰方法参数的原因是防止方法参数在调用时被篡改，其实也就是这个原因，
     * 但理解起来可能会有歧义，我们需要注意的是，在final修饰的方法参数中，如果修饰的是基本类型，
     * 那么在这个方法的内部，基本类型的值是不能够改变的，但是如果修饰的是引用类型的变量，
     * 那么就需要注意了，引用类型变量所指的引用是不能够改变的，但是引用类型变量的值是可以改变的。
     *
     *
     * <注意：引用类型指向不能改变，但是变量的值可以修改/>
     * public static void checkInt(final User user)
     *     {
     *         //user变量的引用是不能够改变的，否则的话，编译会报错
     *         //    user=new User();
     *         //user变量的值是能够修改的，但所指向的引用是不能够改变的
     *         user.setUserName("小王");
     *     }
     *
     */
    @Test
    public void _00(final String s){
        System.out.println(s);
    }


    @Test
    public void _0() {
        Object o = Optional.ofNullable(null).isPresent();
    }

    @Test
    public void _1() {
        boolean ss = StringUtils.equals("-1", "ss");
        System.out.println(ss);
    }

    @Test
    public void _2() {
        List<String> list = new ArrayList<>();

        boolean anyMatch = list.stream().anyMatch(it -> it.equals("1"));
        System.out.println(anyMatch); //false

        boolean noneMatch = list.stream().noneMatch(it -> it.equals("1"));
        System.out.println(noneMatch);//true

        boolean allMatch = list.stream().allMatch(it -> it.equals("1"));
        System.out.println(allMatch); //true
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
     * <p>
     * <p>
     * 正数：例如4>>>2
     * <p>
     * 与4>>2的运算相同，结果也为1
     * <p>
     * <p>
     * 负数：例如-4>>>2
     * <p>
     * 首先写出-4的二进制数,因为是负数所以最高位为1
     * <p>
     * 1000 0000 0000 0000 0000 0000 0000 0100
     * <p>
     * 然后写出-4补码：保证符号位不变，其余位置全部取反，之后再加1（简便方法是：从右往左遇到第一个1，然后剩下的全部取反就是了）
     * <p>
     * 1111 1111 1111 1111 1111 1111 1111 1100（补码）
     * <p>
     * 右移2位： 在高位补0
     * <p>
     * 0011 1111 1111 1111 1111 1111 1111 1111
     * <p>
     * 结果为：1073741823
     */
    @Test
    public void _4() {
        int i = -4 >>> 2;
        System.out.println(i);
    }


    @Test
    public void _5() {
        String source = "";
        String target = "";

        try (
                InputStream fis = new FileInputStream(source);
                OutputStream fos = new FileOutputStream(target)
        ) {
            byte[] buf = new byte[8192];

            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 我们知道，在Java编程过程中，如果打开了外部资源（文件、数据库连接、网络连接等），
         * 我们必须在这些外部资源使用完毕后，手动关闭它们。因为外部资源不由JVM管理，无法享用JVM的垃圾回收机制，
         * 如果我们不在编程时确保在正确的时机关闭外部资源，就会导致外部资源泄露，紧接着就会出现文件被异常占用，
         * 数据库连接过多导致连接池溢出等诸多很严重的问题。
         */

        /**
         * JDK1.7提供的
         * try括号内的资源会在try语句结束后自动释放
         * (不管有没有异常出现都会释放资源)
         *
         * 前提是这些可关闭的资源必须实现 java.lang.AutoCloseable 接口。
         * InputStream 和OutputStream 父类中一定实现了AutoCloseable接口
         */

        /**
         * <实现原理>
         * try-with-resource并不是JVM虚拟机的新增功能，只是JDK实现了一个语法糖，
         * 当你将上面代码反编译后会发现，其实对JVM虚拟机而言，它看到的依然是之前的写法：
         *
         * public static void main(String[] args) {
         *     try {
         *         FileInputStream inputStream = new FileInputStream(new File("test"));
         *         Throwable var2 = null;
         *
         *         try {
         *             System.out.println(inputStream.read());
         *         } catch (Throwable var12) {
         *             var2 = var12;
         *             throw var12;
         *         } finally {
         *             if (inputStream != null) {
         *                 if (var2 != null) {
         *                     try {
         *                         inputStream.close();
         *                     } catch (Throwable var11) {
         *                         var2.addSuppressed(var11);
         *                     }
         *                 } else {
         *                     inputStream.close();
         *                 }
         *             }
         *
         *         }
         *
         *     } catch (IOException var14) {
         *         throw new RuntimeException(var14.getMessage(), var14);
         *     }
         * }
         *
         * 异常抑制
         * 通过反编译的代码，大家可能注意到代码中有一处对异常的特殊处理：
         *
         * var2.addSuppressed(var11);
         *
         * 这是try-with-resource语法涉及的另外一个知识点，叫做异常抑制。当对外部资源进行处理（例如读或写）时，
         * 如果遭遇了异常，且在随后的关闭外部资源过程中，又遭遇了异常，
         * 那么你catch到的将会是对外部资源进行处理时遭遇的异常，关闭资源时遭遇的异常将被“抑制”但不是丢弃，
         * 通过异常的getSuppressed方法，可以提取出被抑制的异常。
         *
         * 总结
         * 1、当一个外部资源的句柄对象实现了AutoCloseable接口，JDK7中便可以利用try-with-resource语法更优雅的关闭资源，消除板式代码。
         *
         * 2、try-with-resource时，如果对外部资源的处理和对外部资源的关闭均遭遇了异常，“关闭异常”将被抑制，“处理异常”将被抛出，
         * 但“关闭异常”并没有丢失，而是存放在“处理异常”的被抑制的异常列表中。
         */
    }

    public static <T,E> void s(T s,E y){
    }

    @Test
    public void _0_(){
        s("ss","ff");
    }

}
