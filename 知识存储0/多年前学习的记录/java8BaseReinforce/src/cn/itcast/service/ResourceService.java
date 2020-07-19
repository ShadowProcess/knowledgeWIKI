package cn.itcast.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dao.ResourceDao;
import cn.itcast.domain.Resource;

public class ResourceService {

	public void save(Resource r) throws SQLException {

		new ResourceDao().save(r);
	}

	public List<Resource> findAll() throws SQLException {
		return new ResourceDao().findAll();
	}

	public Resource findById(String id) throws SQLException {
		return new ResourceDao().findById(id);
	}

}
