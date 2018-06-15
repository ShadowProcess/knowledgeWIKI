package com.atguigu.spring.annotation.generic;

import org.springframework.stereotype.Service;

//若注解没有指定 bean 的 id, 则类名第一个字母小写即为 bean 的 id
@Service
public class UserService extends BaseService<User>{

}
