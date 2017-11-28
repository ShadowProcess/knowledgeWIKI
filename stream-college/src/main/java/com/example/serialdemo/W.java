package com.example.serialdemo;

import java.io.Serializable;

public class W implements Serializable {
    private static final long serialVersionUID = -2534668029195384931L;


    /**
     * 我们随便找几个Java中实现了序列化接口的类，如String、Integer等，
     * 我们可以发现一个细节，那就是这些类除了实现了Serializable外，还定义了一个serialVersionUID
     *
     * 那么，到底什么是serialVersionUID呢？为什么要设置这样一个字段呢？
     */

    /**
     *
     *   * 虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，
     *      * 一个非常重要的一点是两个类的序列化 ID 是否一致，这个所谓的序列化ID，
     *      * 就是我们在代码中定义的serialVersionUID。
     *      *
     *      * 当实现java.io.Serializable接口的类没有显式地定义一个serialVersionUID变量时候，
     *      * JAVA序列化机制会根据编译的Class自动生成一个serialVersionUID作序列化版本比较用，
     *      * 这种情况下，如果Class文件没有发生变化，就算再编译多次，serialVersionUID也不会变化的。
     *      * 但是，如果发生了变化，那么这个文件对应的serialVersionUID也就会发生变化。
     *      *
     *      * 基于以上原理，如果我们一个类实现了Serializable接口，但是没有定义serialVersionUID，
     *      * 然后序列化。在序列化之后，由于某些原因，我们对该类做了变更，重新启动应用后，
     *      * 我们相对之前序列化过的对象进行反序列化的话就会报错
     *
     */


    /**
     * 例如远程一个类A，本地也有一个类A，二者版本号一样
     *
     * 此时修改本地类A的版本号，然后和远程通信，序列化时，将会导致序列化失败
     *
     */
}
