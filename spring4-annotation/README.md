# 日常学习沉淀

- 事务

```text
@Transactional(propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED,
    noRollbackFor = {BookStockException.class},
    readOnly = false,
    timeout = 1)
```

```text
     - 事务的传播行为《默认情况，spring的声明事务，对所有的异常进行回滚，也可以指定》
     
     PROPAGATION_REQUIRED       -- 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
     PROPAGATION_REQUIRES_NEW   -- 新建事务，如果当前存在事务，把当前事务挂起。 
     PROPAGATION_SUPPORTS       -- 支持当前事务，如果当前没有事务，就以非事务方式执行。 
     PROPAGATION_MANDATORY      -- 支持当前事务，如果当前没有事务，就抛出异常。 
     PROPAGATION_NOT_SUPPORTED  -- 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
     PROPAGATION_NEVER          -- 以非事务方式执行，如果当前存在事务，则抛出异常。 
     PROPAGATION_NESTED         -- 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的*
```

```text
     * 事务的隔离级别：
     *１. ISOLATION_READ_UNCOMMITTED：
     *          这是事务最低的隔离级别，它充许令外一个事务可以看到这个事务未提交的数据。
     *          这种隔离级别会产生脏读，不可重复读和幻像读。
     *２. ISOLATION_READ_COMMITTED：
     *          保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数据
     *３. ISOLATION_REPEATABLE_READ：
     *          这种事务隔离级别可以防止脏读，不可重复读。但是可能出现幻像读。
     *          它除了保证一个事务不能读取另一个事务未提交的数据外，还保证了避免下面的情况产生(不可重复读)。
     *４. ISOLATION_SERIALIZABLE：
     *          这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行。
     *          除了防止脏读，不可重复读外，还避免了幻像读。
     *
     * 注：隔离级别可以认为是4个，也可以认为是5个，4个就是上面4个，对应着JDBC的隔离级别。如果是如果，就还有一个default,是默认的隔离级别，遵循传播属性。
```

```text
readOnly
1.指定事务是否只读，表示这个事务只读取数据但不更新数据，这样可以帮助数据库引擎优化事务
 若真的是只读取数据库值的方法，应设置readOnly=true
 为什么读取要加事务？
 如果你一次执行单条查询语句，则没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性；
 如果你一次执行多条查询语句，例如统计查询，报表查询，在这种场景下，多条查询SQL必须保证整体的读一致性，
 否则，在前条SQL查询之后，后条SQL查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态，
 此时，应该启用事务支持。
```

```text
timeout <单位：秒>
指定强制回滚之前事务可以占用的时间；
如果该事务执行超过这个时间，即使程序不抛出异常，也会被执行回滚
防止事务占用时间过长
```

# mvn scope
```text
compile:    
This is the default scope when no other scope is provided.

Dependencies with this scope are available on the classpath of the project in all build tasks and they're propagated to the dependent projects.

More importantly, these dependencies are also transitive:
<dependency>
    <groupId>commons-lang</groupId>
    <artifactId>commons-lang</artifactId>
    <version>2.6</version>
</dependency>
```

```text
provided:   
This scope is used to mark dependencies that should be provided at runtime by JDK or a container, hence the name.

A good use case for this scope would be a web application deployed in some container, where the container already provides some libraries itself.

For example, a web server that already provides the Servlet API at runtime, thus in our project, those dependencies can be defined with the provided scope:

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
    <scope>provided</scope>
</dependency>

The provided dependencies are available only at compile-time and in the test classpath of the project; what's more, they aren't transitive.
```

```text
Runtime:
The dependencies with this scope are required at runtime, but they're not needed for compilation of the project code. Because of that, dependencies marked with the runtime scope will be present in runtime and test classpath, but they will be missing from compile classpath.

A good example of dependencies that should use the runtime scope is a JDBC driver:

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>6.0.6</version>
    <scope>runtime</scope>
</dependency>
```

```text
Test:
This scope is used to indicate that dependency isn't required at standard runtime of the application, but is used only for test purposes. Test dependencies aren't transitive and are only present for test and execution classpaths.

The standard use case for this scope is adding test library like JUnit to our application:

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```

```text
System:
System scope is much similar to the provided scope. The main difference between those two scopes is that system requires us to directly point to specific jar on the system.

The important thing to remember is that building the project with system scope dependencies may fail on different machines if dependencies aren't present or are located in a different place than the one systemPath points to:

<dependency>
    <groupId>com.baeldung</groupId>
    <artifactId>custom-dependency</artifactId>
    <version>1.3.2</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/custom-dependency-1.3.2.jar</systemPath>
</dependency>
```

```text
Import:
This scope was added in Maven 2.0.9 and it's only available for the dependency type pom. We're going to speak more about the type of the dependency in future articles.

Import indicates that this dependency should be replaced with all effective dependencies declared in it's POM:
<dependency>
    <groupId>com.baeldung</groupId>
    <artifactId>custom-project</artifactId>
    <version>1.3.2</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```


# mvn [optional] & [exclusions]
```
optional和exclusions皆用来排除jar包依赖使用的，两者在使用上却是相反

optional定义后，依赖只能在本项目中传递，不会传递到引用该项目的父项目中，父项目需要主动引入该依赖才行
exclusion则是主动排除子项目传递过来的依赖
```

# [/**] 和 [**/]
/*  是拦截所有的文件夹，不包含子文件夹

/** 是拦截所有的文件夹及里面的子文件夹

**/ 表示所有上级路径中，包括多级目录


##自定义starter
1.这个场景需要使用到的依赖是什么？

2.如何编写自动配置
```
@Configuration //指定这是一个配置列
@ConditionalOnXXX() //在指定条件成立的情况下自动配置生效
@AutoConfigureOrder() //指定自动配置类的顺序
@Bean //给容器中添加组件

@ConfigurationProperties结合相关xxxProperteis类来绑定相关的配置
@EnableConfigurationProperties //让xxxProperteis生效加入到容器中

自动配置类要能加载
将需要启动就加载的自动配置类，配置在META-INF/spring.factories
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
```
3.模式
启动器只用来做依赖导入；
专门来写一个自动配置模块；
启动器依赖自动配置；别人只需要引入启动器(starter)

mybatis-spring-boot-starter; 自定义启动器名-spring-boot-starter


![avatar](/home/picture)

[百度](http://baidu.com)
