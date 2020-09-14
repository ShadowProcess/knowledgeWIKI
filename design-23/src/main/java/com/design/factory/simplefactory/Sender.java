package com.design.factory.simplefactory;

/**
 * 简单工厂模式演示
 */

//首先，创建二者的共同接口：
public interface Sender {
    public void send();
}

//其次，创建实现类：
class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is mail sender!");
    }
}

class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}


//最后，建工厂类：
class SendFactory {
    public Sender produce(String type) {
        if ("main".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("没有此类型");
            return null;
        }
    }
}

//我们测试下
class SimpleFactoryTest {
    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender sender = factory.produce("sms");
        sender.send();
    }
}
//输出：this is sms sender!