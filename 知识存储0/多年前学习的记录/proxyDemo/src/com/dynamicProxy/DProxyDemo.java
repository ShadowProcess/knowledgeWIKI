package com.dynamicProxy;

import com.staticProxy.KindWoman;
import com.staticProxy.Pjl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DProxyDemo {

    public static void main(String[] args) {

        /******给潘金莲做动态代理********/
        final KindWoman woman = new Pjl();
        //做一个Pjl的代理,取代了王婆的位置
        KindWoman proxy = (KindWoman) Proxy.newProxyInstance(woman.getClass().getClassLoader(), woman.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(woman, args);
            }
        });
        proxy.throwEye();

        /******给陈冠希做代理********/
        final KindMan man = new Cgx();
        KindMan proxyMan = (KindMan) Proxy.newProxyInstance(man.getClass().getClassLoader(), man.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(man, args); //相当于 man.方法名(参数)
            }
        });
        proxyMan.pz();

    }

}
