package com.design.principle.inversion.improve;

public class DependencyInversion {
    public static void main(String[] args) {
        //客户端无需改变
        Person person = new Person();
        person.receive(new Email());
        person.receive(new Weixin());
    }
}

//定义接口
interface IReceiver{
    public String getInfo();
}

class Email implements IReceiver{
    public String getInfo(){
        return "电子邮件信息";
    }
}

class Weixin implements IReceiver{
    @Override
    public String getInfo() {
        return "微信";
    }
}

//方式2
class Person{
    //对接口的依赖
    public void receive(IReceiver receiver){
        System.out.println(receiver.getInfo());
    }
}