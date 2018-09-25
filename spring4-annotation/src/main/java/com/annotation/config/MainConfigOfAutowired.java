package com.annotation.config;


import com.annotation.bean.Car;
import com.annotation.bean.Color;
import com.annotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配：
 *  spring利用依赖注入(DI);完成对IOC容器中各个组件的依赖关系赋值
 *
 *  1)@Autowired  自动注入；
 *      1）、默认优先按照类型去容器中找对应的组件；applicationContext.getBean(BookDao.class);找到就赋值
 *      2）、如果找到多个相同类型的组件，再将属性名称作为组件的id去容器中查找
 *                                  applicationContext.getBean("bookDao")
 *      3）、@Qualifier("bookDao") //@Qualifier注解表示明确的指定我要装配的组件id,而不是使用属性名
 *      4）、自动装配默认一定要将属性赋值好，没有就会报错
 *              可以使用@Autowired(required = false) //默认是true  ；意思是找到就装配，找不到不装配
 *      5)、@Primary；让spring进行自动装配的时候，默认使用首选的bean;
 *                  可以继续使用@Qualifier指定需要装配bean的名字
 *      BookService{
 *          @Autowired
 *          BookDao bookDao;
 *      }
 *
 *
 *  2）、spring还支持使用@Resource(JSR250)和@Inject(JSR330) 【JSR是java规范注解】
 *          @Resource：
 *              可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配的;【如果找不到名称一样的组件，就报错】
 *              没有能支持@Primary功能，没有支持@Autowired(required=false);
 *
 *          @Inject
 *          【需要导入javax.inject包】，和Autowired的功能一样；@Inject没有required=false的功能

 *          @Autowired: Spring定义的： @Resource、@Inject都是java规范
 *
 * AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能的
 *
 *
 *
 * 3)、@Autowired：构造器，参数，方法，属性
 *          1）、[标注在方法位置]: @Bean+方法参数；参数会自动从容器中获取；默认不写@Autowired效果是一样的，都能自动装配
 *          2）、标注在构造器上：如果组件只有一个有参构造器，这个有参构造器的@Autowire可以省略，参数位置的组件还是可以自动从容器中获取
 *          3）、放在参数位置上
 *
 *
 * 4)、自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）
 *      自定义组件实现xxxAware; 在创建对象的时候，会调用接口规定的方法注入组件: Aware
 *      把Spring底层的一些组件注入到自定义的Bean中；
 *      xxxAware：功能是使用xxxProcessor;
 *          ApplicationContextAware==>ApplicationContextAwareProcessor
 *
 *
 *
 *
 *
 * //默认加在IOC容器中的组件，容器启动会调用无参数构造器创建对象，再进行初始化赋值操作
 * //当把 @Autowired 标注在有参构造器上，spring创建该Bean就会调用该标注的构造器，而不去调用无参构造器
 *
 * //当该组件中只有一个构造器，构造器上的@Autowired可以省略，
 * //因为spring创建bean要调用构造方法，构造方法中有参数时，[构造器要用的组件，都是从容器中获取]
 *     @Component
 *     class Boss{
 *
 *      private Car car;
 *
 *      //@Autowired  可以省略
 *      public Boss(Car car){
 *          this.car = car;
 *          System.out.println("Boss ....有参数构造器");
 *      }
 *     }
 *
 */

@Configuration
@ComponentScan({"com.annotation.service",
        "com.annotation.dao",
        "com.annotation.bean",
        "com.annotation.controller"})
public class MainConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }


    /**
     * @Bean标注的方法创建对象的时候，方法参数的值从容器中获取 [spring会自动从容器中获取]
     */
    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
