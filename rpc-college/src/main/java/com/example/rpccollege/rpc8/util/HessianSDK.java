package com.example.rpccollege.rpc8.util;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.example.rpccollege.common.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 众所周知大名鼎鼎的开源remoting的框架hessian的速度是非常快的，
 * 有人做过测试：一个UserData类,有一个字符串属性,一个日期属性,一个double属性,分别用java,hessian来序列化一百万次,
 * 结果让人吃惊,不止是hessian序列化的速度要比java的快上一倍,而且hessian序列化后的字节数也要比java的少一倍.
 * 总是疑惑不解，为什么hessian的速度会那么快，这估计还是要归功于它的序列化的实现机制。兴趣上来了，决定看一下它是如何来实现它的序列化的。
 */
public class HessianSDK {
    public static void main(String[] args) throws Exception {
        User user = new User(1, "Sandy");
        byte[] bytes = serialize(user);
        System.out.println(bytes.length);
        User u1 = (User) deserialize(bytes);
        System.out.println(u1);
    }

    public static byte[] serialize(Object o) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        output.close();
        return bytes;
    }

    public static Object deserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Hessian2Input input = new Hessian2Input(bais);
        Object o = input.readObject();
        bais.close();
        input.close();
        return o;
    }
}
