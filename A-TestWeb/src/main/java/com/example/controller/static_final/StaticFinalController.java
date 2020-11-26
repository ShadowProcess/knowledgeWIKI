package com.example.controller.static_final;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 警告：
 * Spring无法注入: static或者final修饰的成员
 * <p>
 * static修饰的变量若想模仿注入，可以使用@PostContruct或者其它方法
 * <p>
 * 原因：
 * <p>
 * 这很容易理解：类静态成员的初始化较早，并不需要依赖实例的创建，所以这个时候Spring容器可能都还没“出生”，谈何依赖注入呢？
 *
 * 1、static存在的主要意义
 *    static的主要意义是在于创建独立于具体对象的域变量或者方法。以致于即使没有创建对象，也能使用属性和调用方法！
 *    static关键字还有一个比较关键的作用就是 用来形成静态代码块以优化程序性能。static块可以置于类中的任何地方，
 *    类中可以有多个static块。在类初次被加载的时候，会按照static块的顺序来执行每个static块，并且只会执行一次。
 *   为什么说static块可以用来优化程序性能，是因为它的特性:只会在类加载的时候执行一次。
 *   因此，很多时候会将一些只需要进行一次的初始化操作都放在static代码块中进行。
 *
 * 2、static的独特之处
 *      1、被static修饰的变量或者方法是独立于该类的任何对象，也就是说，这些变量和方法不属于任何一个实例对象，而是被类的实例对象所共享。
 *         怎么理解 “被类的实例对象所共享” 这句话呢？就是说，一个类的静态成员，它是属于大伙的【大伙指的是这个类的多个对象实例，
 *         我们都知道一个类可以创建多个实例！】，所有的类对象共享的，不像成员变量是自个的【自个指的是这个类的单个实例对象】...
 *         我觉得我已经讲的很通俗了，你明白了咩？
 *      2、在该类被第一次加载的时候，就会去加载被static修饰的部分，而且只在类第一次使用时加载并进行初始化，注意这是第一次用就要初始化，
 *        后面根据需要是可以再次赋值的。
 *      3、static变量值在类加载的时候分配空间，以后创建类对象的时候不会重新分配。赋值的话，是可以任意赋值的！
 *      4、被static修饰的变量或者方法是优先于对象存在的，也就是说当一个类加载完毕之后，即便没有创建对象，也可以去访问。
 *
 * 3、static应用场景
 *      因为static是被类的实例对象所共享，因此如果某个成员变量是被所有对象所共享的，那么这个成员变量就应该定义为静态变量。
 *      因此比较常见的static应用场景有：
 *      1、修饰成员变量
 *      2、修饰成员方法
 *      3、静态代码块
 *      4、修饰类【只能修饰内部类也就是静态内部类】
 *      5、静态导包
 *
 *  4、静态变量和实例变量的概念
 *      静态变量：
 *      static修饰的成员变量叫做静态变量【也叫做类变量】，静态变量是属于这个类，而不是属于是对象。
 *      实例变量：
 *      没有被static修饰的成员变量叫做实例变量，实例变量是属于这个类的实例对象。
 *      还有一点需要注意的是：static是不允许用来修饰局部变量，不要问我问什么，因为java规定的！
 *
 * 5、静态变量和实例变量区别【重点常用】
 *      静态变量：
 *      静态变量由于不属于任何实例对象，属于类的，所以在内存中只会有一份，在类的加载过程中，JVM只为静态变量分配一次内存空间。
 *      实例变量：
 *      每次创建对象，都会为每个对象分配成员变量内存空间，实例变量是属于实例对象的，在内存中，创建几次对象，就有几份成员变量。
 *
 */

@Controller
public class StaticFinalController {

    @Value("${abc:s}")
    private static String s;
    @Value("${avc:aFinal}")
    private final String a = "a";

    @GetMapping("/ok")
    @ResponseBody
    public String get() {
        System.out.println(s); //null
        System.out.println(a); //a
        return "ok";
    }
}


/**
 * 虽然Main方法什么也没有；但是
 * 运行结果：
 * test static 1
 * test static 2
 */
class Test {
    static {
        System.out.println("test static 1");
    }
    static {
        System.out.println("test static 2");
    }
    public static void main(String[] args) {
    }
}

