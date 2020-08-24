package com.example.jpademo.jpa.entity.enum_type;

/**
 * 枚举类型
 * 在mysql以 int类型对应
 * 每个枚举类型从0开始，依次往上加1
 */
public enum AuditState {
    A(1,"哈哈"),  //存入数据库为 0
    B(2,"呵呵"),  //存入数据库为 1
    C(3,"吼吼"),  //存入数据库为 2
    D(4,"晋级");  //存入数据库为 3
    //。。。Z(n,"XXX")       //存入数据库为 4

    private int code;
    private String name;

    AuditState(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
