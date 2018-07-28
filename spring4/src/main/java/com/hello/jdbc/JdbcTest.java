package com.hello.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不支持级联属性，jdbcTemplate到底是一个jdbc的小工具，而不是一个orm框架
 */
public class JdbcTest {
    private ApplicationContext ctx = null;
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private BookDao bookDao;

    {
        ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        bookDao = ctx.getBean(BookDao.class);
        namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
    }

    @Test
    public void testDatasource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    /**
     * 执行批量更新： 批量insert，update,delete
     * 最后一个参数是object[]的list类型 因为修改一条记录需要一个object的数组，那么多条不就需要多个object数组
     */
    @Test
    public void testBatch() {
        String sql = "insert into book(book_id,book_cover,book_info,book_name,book_price) values(?,?,?,?,?)";
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{100, "fff", "fff", "fff", 20});
        batchArgs.add(new Object[]{200, "fff1", "fff1", "fff1", 30});
        batchArgs.add(new Object[]{300, "fff2", "fff2", "fff2", 40});
        batchArgs.add(new Object[]{400, "fff3", "fff3", "fff3", 50});
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Test
    public void testUpdate() {
        String sql = "update book set book_name=? where book_id = ?";
        jdbcTemplate.update(sql, "jack", 1);
    }

    @Test
    public void testSelect() {
        String sql = "select * from book where book_id = ?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        Book book = jdbcTemplate.queryForObject(sql, rowMapper, 1);
        System.out.println(book);
    }


    /**
     * 查到实体类的集合
     */
    @Test
    public void testQueryForList() {
        String sql = "select * from book where book_id > ?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        List<Book> books = jdbcTemplate.query(sql, rowMapper, 5);
        System.out.println(books);
    }


    /**
     * 获取单个列的值，或做统计查询
     */
    @Test
    public void testQueryForObject() {
        String sql = "select count(book_id) from book";
        long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(count);
    }


    @Test
    public void testDao() {
        Book book = bookDao.get(1);
        System.out.println(book);
    }




    /**
     * 可以给参数指定名字
     * 1.好处：如有多个参数，不需要再去对应顺序，直接对应参数名，便于维护
     * 2.坏处：较为麻烦
     */
    @Test
    public void NamedParameterJdbcTemplate() {
        String sql = "insert into book(book_id,book_info,book_price) values(:id,:bi,:bp)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 999);
        paramMap.put("bi", "hhhhh");
        paramMap.put("bp", 333);
        namedParameterJdbcTemplate.update(sql, paramMap);
    }


    /**
     * 使用具名参数时，可以使用下面方式进行更新操作
     *
     * 1.sql语句中的参数名和类的属性一致
     * 2.使用SqlParameterSource作为参数
     *
     */
    @Test
    public void NamedParameterJdbcTemplate1() {
        String sql = "insert into book(book_id,book_info,book_price) values(:bookId,:bookInfo,:bookPrice)";

        Book book = new Book();
        book.setBookId(10000L);
        book.setBookInfo("黑胡椒");
        book.setBookPrice(new BigDecimal(20.0));

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

}
