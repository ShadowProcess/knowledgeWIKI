package com.example.spi_college;

import com.example.spi_college.spi.SpiService;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * JDK SPI是什么 (新版的DriverManager就是用这个实现的)
 *
 * 最近工作中听几个同事说了好几次SPI这个名词，虽然和我没关系，但是心里默默想还是学习一下，不然下次和我说到SPI，
 * 连是什么都不知道那就尴尬了。
 *
 * 所以SPI是什么呢？SPI全称Service Provider Interface，在Java中还是一个比较重要的概念，
 * 是Java提供的一套用来被第三方实现或者扩展的API，或者换句话说，SPI是一种服务发现机制。
 *
 * JDK SPI使用说明及示例
 *
 * 要使用SPI比较简单，只需要按照以下几个步骤操作即可：
 *
 * 在jar包的META-INF/services目录下创建一个以"接口全限定名"为命名的文件，内容为实现类的全限定名
 * 接口实现类所在的jar包在classpath下
 * 主程序通过java.util.ServiceLoader动态状态实现模块，它通过扫描META-INF/services目录下的配置文件找到实现类的全限定名，把类加载到JVM
 * SPI的实现类必须带一个无参构造方法
 */
public class SpiTest {
    @Test
    public void testSpi() {
        ServiceLoader<SpiService> serviceLoader = ServiceLoader.load(SpiService.class);

        Iterator<SpiService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            SpiService spiService = iterator.next();
            spiService.hello();
        }
    }

}
