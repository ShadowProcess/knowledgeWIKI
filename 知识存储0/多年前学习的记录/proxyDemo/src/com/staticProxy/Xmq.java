package com.staticProxy;

//西门庆
public class Xmq {
    public static void main(String[] args) {
        KindWoman woman = new Pjl();
        Wp wp = new Wp(woman);

        /**
         * 真实执行的是潘金莲，
         * 但是我们看不到，
         * 所以屏蔽了真实行为。
         */
        wp.throwEye();

    }

}
