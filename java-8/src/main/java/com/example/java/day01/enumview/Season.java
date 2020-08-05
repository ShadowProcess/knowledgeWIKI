package com.example.java.day01.enumview;

/**
 *
 * 在分析之前，先来回顾下枚举类的基础知识点
 * 枚举类一般用于一个类只可能拥有有限个实例，比如季节只可拥有春夏秋冬，性别只有男女
 * 枚举类和普通类有以下几个不同点：
 * 1、枚举类不能指定继承的父类（因为继承了java.lang.Enum类），但是可以实现多个接口，枚举类默认实现了Comparable接口和Serializable接口
 * 2、枚举类的构造方法的访问权限只可为private
 * 3、枚举类的实例必须显式列出。
 *
 * 枚举类其实是继承了Enum这个抽象类
 */
public enum Season {
    SPRING("春天"), SUMMER("夏天"), AUTUMN("秋天"), WINTER("冬天");

    private final String chinese;

    private Season(String chinese) {
        this.chinese = chinese;
    }

    public String getChinese() {
        return chinese;
    }
}

////上述代码相当于
//public final class Season extends Enum<Season> {
//    public static final Season SPRING;
//    public static final Season SUMMER;
//    public static final Season AUTUMN;
//    public static final Season WINTER;
//    private static final Season[] ENUM$VALUES;
//
//    static {
//        SPRING = new Season("SPRING", 0, "春天");
//        SUMMER = new Season("SUMMER", 1, "夏天");
//        AUTUMN = new Season("AUTUMN", 2, "秋天");
//        WINTER = new Season("WINTER", 3, "冬天");
//        ENUM$VALUES = new Season[]{SPRING, SUMMER, AUTUMN, WINTER}
//    }
//
//    private final String chinese;
//
//    private Season(String name, int ordinal, String chinese) {
//        super(name, ordinal);
//        this.chinese = chinese;
//    }
//
//    public String getChinese() {
//        return chinese;
//    }
//
//    public static Season[] values() {
//        Season[] arr = new Session[ENUM$VALUES.length];
//        System.arraycopy(ENUM$VALUES, 0, arr, 0, arr.length);
//        return arr;
//    }
//
//    public static Season valueOf(String name) {
//        return Enum.valueOf(Season.class, name);
//    }
//}

//在编译过程中，编译器会对枚举类进行以下处理：
//        1、增加一个静态初始化块和一个静态数组，静态初始化块负责构造枚举类的实例并将其保存到数组中。
//        2、修改当前枚举类的构造方法，向构造方法前面增加两个参数：name, ordinal。分别代表枚举类的名称和初始化顺序，并且使用super调用父类java.lang.Enum的构造方法。
//        3、添加两个静态方法：
//public static Season[] values()
//        该方法返回一个数组，包含了该枚举类所有的实例（并不是返回枚举类自己保存实例的数组，而是通过对其复制，以确保实例不被篡改）。
//public static Season valueOf(String name)
//        该方法通过当前枚举类的名称返回对应的实例。比如valueOf(“SPRING”)返回Season.SPRING）

