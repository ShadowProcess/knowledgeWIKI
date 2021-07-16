package com.example.rpccollege.rpc6;


import com.example.rpccollege.common.IUserService;


public class Client {
    public static void main(String[] args)throws Exception {
        IUserService service = (IUserService) Stub.getStub(IUserService.class);
        System.out.println(service.findUserById(123));
    }
}
