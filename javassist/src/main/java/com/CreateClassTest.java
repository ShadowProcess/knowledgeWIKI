package com;

import javassist.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Java字节码以二进制的形式存储在 .class 文件中，每一个 .class 文件包含一个Java类或接口。
 * Javassist 就是一个用来处理Java字节码的类库。
 * 它可以在一个已经编译好的类中添加新的方法，或者是修改已有的方法，
 * 并且不需要对字节码方面有深入的了解。同时也可以去生成一个新的类对象，通过完全手动的方式
 *
 * @author Alex
 */

public class CreateClassTest {

    private static final String classPath = "D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\knowledgeWIKI\\javassist\\src\\main\\java";

    /**
     * 创建字节码文件
     *
     * @throws Exception
     */
    public static void createClass() throws Exception {
        ClassPool pool = ClassPool.getDefault();

        //1.创建一个空类
        CtClass cc = pool.makeClass("com.Person");

        //2.新增一个字段 private String name;  字段名为name
        CtField param = new CtField(pool.get("java.lang.String"), "name", cc);
        //设置访问级别 private
        param.setModifiers(Modifier.PRIVATE);
        //初始值是 “xiaoming”
        cc.addField(param, CtField.Initializer.constant("xiaoming"));

        //3.生成 get,set方法
        cc.addMethod(CtNewMethod.setter("setName", param));
        cc.addMethod(CtNewMethod.setter("getName", param));

        //4.添加无参数构造
        CtConstructor cons = new CtConstructor(new CtClass[]{}, cc);
        cons.setBody("{ name = \"xiaoming\";}");
        cc.addConstructor(cons);

        //5.添加有参数构造函数
        cons = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, cc);
        //TODO $0=this / $1,$2,$3... 代表方法参数
        cons.setBody("{$0.name = $1;}");
        cc.addConstructor(cons);

        //6. 创建一个名为printName方法，无参数，无返回值，输出name值
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        cc.addMethod(ctMethod);

        //这里会将这个创建的类对象编译为.class文件
        cc.writeFile(classPath);
    }

    /**
     * 修改字节码文件
     *
     * @throws Exception
     */
    public static void update() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(classPath);
        CtClass cc = pool.get("com.Person");

        //1.在方法前后增加代码
        /**
         *  public void printName() {
         *      System.out.println("起飞之前准备降落伞");
         *      System.out.println(this.name);
         *      Object var2 = null;
         *      System.out.println("成功落地。。。。");
         *  }
         *     比如常见的日志切面，权限切面。我们利用javassist来实现这个功能。
         */
        CtMethod printName = cc.getDeclaredMethod("printName");
        printName.insertBefore("System.out.println(\"起飞之前准备降落伞\");");
        printName.insertAfter("System.out.println(\"成功落地。。。。\");");

        //2.新增一个方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "joinFriend", new CtClass[]{}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(\"i want to be your friend\");}");
        cc.addMethod(ctMethod);

        //3.调用我们修改的字节码文件的新增方法
        Object person = cc.toClass().newInstance();
        Method execute = person.getClass().getMethod("joinFriend");
        execute.invoke(person);

        //4.调用方法前后增加代码的方法
        Method printName1 = person.getClass().getMethod("printName");
        printName1.invoke(person);
    }

    /**
     * 让字节码文件实现接口
     */
    public static void  impInterface() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(classPath);

        //1.获取接口
        CtClass ctClassI = pool.get("com.IPerson");
        //2.获取上面生成的类
        CtClass ctClass = pool.get("com.Person");

        //3.使代码生成的类，实现PersonI接口
        ctClass.setInterfaces(new CtClass[]{ctClassI});

        //4.把改变写入class文件
        ctClass.writeFile(classPath);

        //5.以下通过接口直接调用 强转
        IPerson person = (IPerson) ctClass.toClass().newInstance();
        person.printName();
    }


    @Test
    public void _create() throws Exception {
        createClass();
        //使用代码生成的Class创建对象
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(classPath);
        CtClass ctClass = pool.get("com.Person");
        Object person = ctClass.toClass().newInstance();
        System.out.println(person);
    }

    @Test
    public void _update() throws Exception {
        update();
    }

    @Test
    public void _impInterface() throws Exception {
       impInterface();
    }
}
