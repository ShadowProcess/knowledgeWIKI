package com.example.jpademo.jpa.entity.integer_type;

import javax.persistence.*;

/**
 *  xxx int(M)
 *
 *
 * mysql在建表的时候int类型后的长度代表什么? 是该列允许存储值的最大宽度吗? 为什么我设置成int(1), 也一样能存10,100,1000呢?
 *
 * 当时我虽然知道int(1),这个长度1并不代表允许存储的宽度,但却没有一个合理的解释.
 * 或者说对这个长度也没有真正的研究过到底代表什么, 平时都用int(11), 也不知道为什么要11位.
 * 所以我在网上查阅了一些资料, 也仔细的看了mysql手册关于int data type的说法.
 *
 * mysql手册中这个长度/值用"M"来表示的. 细心的朋友应该有注意到过mysql手册上有这么一句话:
 * M指示最大显示宽度。最大有效显示宽度是255。显示宽度与存储大小或类型包含的值的范围无关;
 *
 * 这句话看上去不太容易理解, 因为这里有个关键词容易让我们混淆, "最大显示宽度"我们第一反应是该字段的值最大能允许存放的值的宽度.
 * 以为我们建了int(1),就不能存放数据10了, 其实不是这个意思.
 *
 * 这个M=5我们可以简单的理解成为, 我们建立这个长度是为了告诉MYSQL数据库我们这个字段的存储的数据的宽度为5位数,
 * 当然如果你不是5位数(只要在该类型的存储范围之内)MYSQL也能正常存储,  这也就能解释以上标红的话.
 *
 * 我们把这个字段的"属性"修改为UNSIGNED ZEROFILL看一下效果.
 * 我们看到现在我的number字段, 长度(M)=5, 属性=UNSIGNED ZEROFILL(无符号,用0来填充位数),
 * 设置这个属性后我往表时插入数据,系统会自动把number字段M不够5位的在左侧用0来填充; 效果如下
 *
 * {需要使用命令行才能看到，软件中可能看不到}
 * mysql> select * from jpa_int;
 * +----+------+-------+-------------+------+
 * | id | a    | b     | c           | e    |
 * +----+------+-------+-------------+------+
 * |  3 |    1 | 00001 | 00000000001 |    1 |
 * +----+------+-------+-------------+------+
 */

@Entity
@Table(name = "jpa_int")
public class IntegerDemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int(1) UNSIGNED ZEROFILL default 1")
    private Integer a;
    @Column(columnDefinition = "int(5) UNSIGNED ZEROFILL default 1")
    private Integer b;
    @Column(columnDefinition = "int(11) UNSIGNED ZEROFILL default 1")
    private Integer c;
    //不指定
    private Integer e;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getE() {
        return e;
    }

    public void setE(Integer e) {
        this.e = e;
    }
}
