#一级缓存：
```
同一个EntityManager中不用重复在数据库中查询同一个数据,EntityManager关闭了,一级缓存就失效了.
```
#二级缓存：
```
<shared-cache-mode>节点：若JPA的实现支持二级缓存，该节点可以配置在当前的持久化单元中是否启用二级缓存，可配置如下值：
    ALL：所有的实体类都被缓存.
    NONE：所有的实体类都不被缓存.
    ENABLE_SELECTIVE：标识 @Cacheable(true) 注解的实体类将被缓存.
    DISABLE_SELECTIVE：缓存除标识 @Cacheable(false) 以外的所有实体类.
    UNSPECIFIED：默认值，JPA产品默认值将被使用.

二级缓存：命中条件：同一个EntityManagerFactory，不同的EntityManager，同一个OID(对象唯一标识)
所谓的二级缓存，也就是可以跨entityManager的缓存，也就是说：就算你关闭了entityManager，缓存也依然在。
```

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
