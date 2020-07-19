package com.dao;

import com.domain.User;
import com.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserDao {

    public User findUserByUserNameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from users where username=? and password=?";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

        return runner.query(sql,new BeanHandler<User>(User.class),username,password);
    }


    //查询指定用户的权限
//    select users.id,privileges.name
//    from users,privilege,userprivilege
//    where users.id = userprivilege.user_id
//    and privileges.id = userprivilege.privilege_id
//    and users.id = ?;
}
