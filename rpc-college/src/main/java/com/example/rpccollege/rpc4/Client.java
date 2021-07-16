package com.example.rpccollege.rpc4;


import com.example.rpccollege.common.IUserService;

public class Client {
    public static void main(String[] args)throws Exception {
        IUserService service = Stub.getStub();
        System.out.println(service.findUserById(123));
    }
}
