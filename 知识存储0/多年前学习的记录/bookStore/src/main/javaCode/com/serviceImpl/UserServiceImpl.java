package main.javaCode.com.serviceImpl;

import com.dao.UserMapper;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements UserService {


    /**这个注入由Spring和Mybatis管理,不需要实现类**/
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userMapper.selectByUserName(userName);
    }
}
