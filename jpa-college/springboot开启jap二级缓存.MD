--------------------------------------JPA一级缓存-----------------------------------------------
#JPA默认是开启一级缓存的
#一级缓存就是同一个entityManagerFactory 同一个entityManager 同oid（默认的）
#entityManager关闭了，一级缓存就失效了
```
也就是在同一个事务中，对同一个实体执行查询操作，第二次从缓存中取，不会重复执行SQL
```
----------------------------------------------------------------------------------------------




[jpa的缓存分为一级缓存和二级缓存，一级缓存是会话级别的，而二级缓存是跨会话级别的。]





--------------------------------------JPA二级缓存-----------------------------------------------
jpa没有集合缓存的概念，而是hibernate里特有的，应此entityManager.getEntityManagerFactory().getCache().evictAll();  只清空实体缓存，而实体对应的集合缓存还是存在的，因此不会查数据库获取最新的。

- 适用场景
 1.查询多于增删改
 2.财务金融等敏感数据不适合
 3.数据量太大不适合
 4.数据库被多方系统增删改不适合
- 二级缓存：命中条件：同一个EntityManagerFactory，不同的EntityManager，同一个OID
- 使用二级缓存 [所谓的二级缓存，也就是可以跨entityManager的缓存，也就是说：就算你关闭了entityManager，缓存也依然在。]
#1.导入依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-ehcache</artifactId>
    <exclusions>
        <exclusion>
            <groupId>net.sf.ehcache</groupId>
            <artifactId>ehcache-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>3.5.2</version>
</dependency>
```

#2.在配置文件中配置
```
#开启二级缓存
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.cache.use_query_cache=true
#表示，在指定实体上增加@Cacheable时，该类才会被二级缓存
spring.jpa.properties.javax.persistence.sharedCache.mode=ENABLE_SELECTIVE
#SQL执行时间
hibernate.generate_statistics=true
#指定缓存provider
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
```

#3.在需要缓存的实体上增加注解[指定实体不能有关联实体，设置为加载状态为EAGER，否则不生效]
```
@Cacheable //开启二级缓存，需要相对应的配置, 本类加载设置不能有EAGER，否则二级不生效
```
