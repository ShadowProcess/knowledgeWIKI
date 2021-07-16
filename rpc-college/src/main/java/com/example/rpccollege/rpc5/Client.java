package com.example.rpccollege.rpc5;


import com.example.rpccollege.common.IUserService;
import com.example.rpccollege.common.User;


public class Client {
    public static void main(String[] args)throws Exception {
        // IUserService可以加入新的处理逻辑, User可以自由变动
        IUserService service = Stub.getStub();
        System.out.println((User)service.findUserById(123));
    }
}
