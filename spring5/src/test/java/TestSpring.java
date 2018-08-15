import com.example.basic.Person;
import com.example.basic.constructor.Customer;
import com.example.basic.factorybean.ConnectionFactory;
import com.example.basic.factorybean.ConnectionFactoryBean;
import com.example.basic.life.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class TestSpring {

    @Test
    public void test(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }

    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Person person = ctx.getBean("person",Person.class);
        //System.out.println(person);

        //保证当前Spring配置文件中只能有一个<bean class是person类型>
        //Person bean = ctx.getBean(Person.class);
        //System.out.println(bean);

        //获取的是Spring工厂配置所有Bean标签的id值
        //String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        //for (String beanDefinitionName : beanDefinitionNames) {
        //    System.out.println(beanDefinitionName);
        //}

        //根据类型获取Spring配置文件对应的id
        //String[] beanNamesForType = ctx.getBeanNamesForType(Person.class);
        //for (String s : beanNamesForType) {
        //    System.out.println(s);
        //}

        //用于判断是否存在bean定义； 不能判断name
        //boolean person = ctx.containsBeanDefinition("person");

        //用于判断是否存在bean,和上面有区别  可以判断name
        //boolean person1 = ctx.containsBean("person");
    }


    //测试不写id
    @Test
    public void test2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Person bean = ctx.getBean(Person.class);
        //System.out.println(bean);
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    //测试name属性
    @Test
    public void test3(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object p = ctx.getBean("/person");
        System.out.println(p);
    }


    //测试注入
    @Test
    public void test4(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person p = (Person) ctx.getBean("/person");

        p.setId(1);
        p.setName("al");
        System.out.println(p);
    }

    //测试注入
    @Test
    public void test5(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person p = (Person) ctx.getBean("/person");

        for (String s : p.getList()) {
            System.out.println(s);
        }

        for (String s : p.getMap().keySet()) {
            System.out.println(s);
        }
        System.out.println(p);
    }

    //测试注入
    @Test
    public void test6(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Customer p = (Customer) ctx.getBean("customer");
        System.out.println(p);
    }


    //测试工厂得到的对象
    @Test
    public void test7(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection p = (Connection) ctx.getBean("conn");
        System.out.println(p);
    }

    //得到工厂自己
    @Test
    public void test8(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionFactoryBean p = (ConnectionFactoryBean) ctx.getBean("&conn");
        System.out.println(p);
    }

    @Test
    public void test9(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection conn = (Connection) ctx.getBean("conn");
        System.out.println(conn);
    }

    //得到工厂自己
    @Test
    public void test10(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Product conn = (Product) ctx.getBean("product");
        System.out.println(conn);
    }



    @Test
    public void test11(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        Object person = ctx.getBean("person");
        System.out.println(person);
    }

    @Test
    public void test12(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext3.xml");
        Object c = ctx.getBean("c");
        System.out.println(c);
    }



}
