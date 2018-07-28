package com.hello.transaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SpringTransaction {

    private ApplicationContext ctx = null;
    private BookShopDao bookShopDao = null;
    private BookService bookService = null;

    //先于构造方法执行,次于静态代码块执行
    {
        ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        bookShopDao = ctx.getBean(BookShopDao.class);
    }


    @Test
    public void testFindBook(){
        bookShopDao.findBookPriceByIsbn("100");
    }

    @Test
    public void testBookStock(String isbn){
        //检查书的库存是否足够，不够抛出异常
        if (isbn == "0") {
            throw new BookStockException("库存不足");
        }
        bookShopDao.updateBookStock(isbn);
    }

    @Test
    public void testUserPrice(String username,int price){
        if (price == 0) {
            throw new BookStockException("余额不足");
        }
        bookShopDao.updateUserAccount(username,price);
    }


    /**
     * 事务的传播行为《默认情况，spring的声明事务，对所有的异常进行回滚，也可以指定》
     *
     PROPAGATION_REQUIRED       -- 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
     PROPAGATION_REQUIRES_NEW   -- 新建事务，如果当前存在事务，把当前事务挂起。 
     PROPAGATION_SUPPORTS       -- 支持当前事务，如果当前没有事务，就以非事务方式执行。 
     PROPAGATION_MANDATORY      -- 支持当前事务，如果当前没有事务，就抛出异常。 
     PROPAGATION_NOT_SUPPORTED  -- 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
     PROPAGATION_NEVER          -- 以非事务方式执行，如果当前存在事务，则抛出异常。 
     PROPAGATION_NESTED         -- 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的*
     */

    /**
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
     */

    /**
     * readOnly
     * 1.指定事务是否只读，表示这个事务只读取数据但不更新数据，这样可以帮助数据库引擎优化事务
     *  若真的是只读取数据库值的方法，应设置readOnly=true
     *
     *  为什么读取要加事务？
     *  如果你一次执行单条查询语句，则没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性；
     *  如果你一次执行多条查询语句，例如统计查询，报表查询，在这种场景下，多条查询SQL必须保证整体的读一致性，
     *  否则，在前条SQL查询之后，后条SQL查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态，
     *  此时，应该启用事务支持。
     */

    /**
     * timeout <单位：秒>
     * 指定强制回滚之前事务可以占用的时间；
     * 如果该事务执行超过这个时间，即使程序不抛出异常，也会被执行回滚
     * 防止事务占用时间过长
     */
    //添加事务注解
    @Transactional(propagation = Propagation.REQUIRED,
    isolation = Isolation.READ_COMMITTED,
    noRollbackFor = {BookStockException.class},
    readOnly = false,
    timeout = 1)
    @Test
    public void testTran(){
        bookService.purchase("aa","1001");
    }

}
