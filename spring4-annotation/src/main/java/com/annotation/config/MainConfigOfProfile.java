package com.annotation.config;

import com.annotation.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 *Profile：
 *  spring为我们提供的根据当前环境，动态的激活和切换一系列组件的功能
 *
 * 开发环境，测试环境，生产环境
 * 数据源：(/A)(/B)(/C);
 *
 * @Profile:指定组件在哪个环境的情况下，才能被注册到容器，不指定，任何环境都能注册该组件
 *
 * 1)、加了环境标识的bean，只有这个环境被激活的时候，才能注册到容器中。默认是default环境
 * 2)、写在配置类上，只有是在指定的环境的时候，整个配置类里面的所有配置才能生效
 * 3)、没有标识环境标识的Bean在，任何环境下都是加载的；
 */


//@Profile("test")
@PropertySource({"classpath:/dbconfig.properties"})
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {


    @Value("${db.user}")
    private String user;

    private StringValueResolver valueResolver;

    private String driverClass;


    @Bean
    public Yellow yellow(){
        return new Yellow();
    }


    //@Profile("default") 默认的
    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/book_demo");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/imooc_demo");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        this.driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
