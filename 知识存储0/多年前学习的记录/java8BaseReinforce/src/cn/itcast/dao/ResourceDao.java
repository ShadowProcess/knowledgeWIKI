package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.domain.Resource;
import cn.itcast.utils.DataSourceUtils;

public class ResourceDao {

	public void save(Resource r) throws SQLException {

		String sql = "insert into resources values(null,?,?,?,null,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		runner.update(sql, r.getUuidname(), r.getRealname(), r.getSavepath(),
				r.getDescription());
	}

	public List<Resource> findAll() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		return runner.query("select * from resources",
				new BeanListHandler<Resource>(Resource.class));

	}

	public Resource findById(String id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		return runner.query("select * from resources where id=?",
				new BeanHandler<Resource>(Resource.class), id);
	}

}
