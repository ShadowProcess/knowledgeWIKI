package com.fenye;

import com.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class Service {


    /**
     * 分页操作
     *
     * @param pageNum          页码
     * @param currentPageCount 每页条数
     * @return
     */
    public PageBean finByPage(int pageNum, int currentPageCount) throws SQLException {
        PageBean pageBean = new PageBean();


        String sql = "select * from customer limit ?,?";
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        List cs = runner.query(sql, new BeanListHandler<Customer>(Customer.class), (pageNum - 1) * currentPageCount, currentPageCount);

        //总记录数
        int totalCount = findAllCount();

        //总页数
        int totalPage = (int) Math.ceil(totalCount * 1.0 / currentPageCount);


        pageBean.setTotalCount(totalCount); //总记录数
        pageBean.setTotalPage(totalPage); //总页数2
        pageBean.setCs(cs); // 封装当前页数据
        pageBean.setCurrentPageCount(pageNum); //封装每页数量
        pageBean.setPageNum(pageNum); //封装当前页码

        return pageBean;
    }

    public int findAllCount() throws SQLException {
        String sql = "select count(*) from customer";
        QueryRunner runner = new QueryRunner();
        long i = (long) runner.query(sql, new ScalarHandler());

        return (int) i;
    }

}
