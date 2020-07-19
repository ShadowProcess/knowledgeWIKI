package com;

import com.annotation.BookInfo;
import com.domain.User;
import com.service.BookService;
import com.service.BookServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class BookServiceFactory {

    private static BookService service = new BookServiceImpl();

    public static BookService getInstance() {

        BookService proxy = (BookService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //判断方法是否有指定注解
                boolean flag = method.isAnnotationPresent(BookInfo.class);
                if (flag == false) {
                    //未设置注解
                    return method.invoke(service, args);
                }


                //获取当前用户
                User currentUser = (User) args[0];
                if (currentUser == null) {
                    throw new RuntimeException("没有登录,请登录后操作!");
                }

                //查询当前用户所具有的的权限
                //    select users.id,privileges.name
                //    from users,privilege,userprivilege
                //    where users.id = userprivilege.user_id
                //    and privileges.id = userprivilege.privilege_id
                //    and users.id = ?;
                List<String> privileges = new ArrayList<>(); //存储当前用户的所有权限


                //真实行为访问前--判断用户是否有权限执行当前行为


                //得到Method方法的注解
                BookInfo info = method.getAnnotation(BookInfo.class);
                String privilegeName = info.value();

                if (privileges.contains(privilegeName)) { //当前用户具有执行该方法的权限
                   return method.invoke(service, args);
                } else {
                    throw new RuntimeException("当前用户权限不足!");
                }

                //真实行为访问后
            }
        });

        return proxy;
    }
}
