package com.staticProxy;

//王婆--代理
public class Wp implements KindWoman{

    private KindWoman woman;

    public Wp(KindWoman woman){
        this.woman = woman;
    }

    @Override
    public void throwEye() {
        //在这里做操作，可以控制是否调用真实行为。
        woman.throwEye();
        //在这个位置，可以在真实行为调用完成后，在做操作
    }

    @Override
    public void doSomething() {
        woman.doSomething();
    }
}
