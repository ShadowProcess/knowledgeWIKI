package com.test;//package com.example.mybatis;
//
//import com.example.mybatis.dao.UserDAO;
//import com.example.mybatis.entity.User;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//  原生mybatis使用方法
//public class TestMybatis {
//
//    public static void main(String[] args) throws IOException {
//
//        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        SqlSession session = sqlSessionFactory.openSession(); //sqlSessionFactory.openSession(true) 可以指定自动提交
//
//        User user = new User();
//        user.setName("sun");
//        user.setPassword("1223");
//
//        UserDAO userDAO = session.getMapper(UserDAO.class);
//        userDAO.save(user);
//
//        session.commit();
//    }
//}
