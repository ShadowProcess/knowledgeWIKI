package com.hello.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * 不推荐使用JdbcDaoSupport
 *
 * 而推荐使用JDBCTemplate作为dao的成员变量
 */

@Repository
public class BookDao extends JdbcDaoSupport {

    @Autowired
    public void setDataSource2(DataSource dataSource){
        setDataSource(dataSource);
    }

    public Book get(Integer id) {
        String sql = "select * from book where book_id = ?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        Book bo = getJdbcTemplate().queryForObject(sql,rowMapper,id);
        return bo;
    }

}
