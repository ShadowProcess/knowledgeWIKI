package com.example.jpademo.jpa.entity.onetoone;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "JPA_DEPARTMENTS")
@Entity
public class Department implements Serializable {
    private Integer id;
    private String deptName;
    private Manager mgr;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "DEPT_NAME")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    //使用@OneToOne映射一对一关联关系
    //若需要在当前数据表中添加主键，则需要使用@JoinColumn来进行映射，注意1-1关联，所以需要添加unique=true
    @JoinColumn(name = "MGR_ID",unique = true)
    @OneToOne(fetch = FetchType.EAGER)
    public Manager getMgr() {
        return mgr;
    }

    public void setMgr(Manager mgr) {
        this.mgr = mgr;
    }
}
