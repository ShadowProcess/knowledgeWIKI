package com.example.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    //@Qualifier("userDaoImpl")

    @Resource //@Resource如果没有指定name属性，当注解标注在字段上时，即默认取字段的名称作为bean名称寻找依赖对象
    private UserDao userDao; //直接放在成员变量上，spring不会调用set方法，spring通过反射直接赋值成员变量

    public void setUserDao(UserDao userDao) {
        System.out.println("调用set方法注入");
        this.userDao = userDao;
    }

    @Override
    public void register() {
        userDao.save();
    }

    /**
     * 注意：如果没有指定name属性，并且按照默认的名称仍然找不到依赖对象时，
     * @Resource注解会回退到按类型装配。但一旦指定了name属性，就只能按名称装配了。
     */
}
