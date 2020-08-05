package com.example.try_with_resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Twr {

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
}
