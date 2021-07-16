package com.example.rpccollege.rpc2;


public class Client {
    public static void main(String[] args)throws Exception {
        Stub stub = new Stub();
        System.out.println(stub.findUserById(123));
    }
}
