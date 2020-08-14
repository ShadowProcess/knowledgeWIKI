package com.example.jpademo.jpa.entity.onetomany;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 当实体类与其映射的数据库表名不同名时，需要使用@Table标注说明
 * 如果一样，则可以不写@Table标明
 */

@Cacheable //开启二级缓存，需要相对应的配置, 本类加载设置不能有EAGER，否则二级不生效
@Entity
@Table(name = "JPA_CUSTOMER") //JPA 默认会将实体中的 TABLE_NAME 转成小写如
public class Customer {
    private Integer id;
    private String lastName;
    private String email;
    private int age;

    private Date createdTime;
    private Date birth;

    private Set<Order> orderSet = new HashSet<>();


   /*
    指定主键生成的表（主键从另外一个表生成）
    ID | PK_NAME     | PK_VALUE
    1  | CUSTOMER_ID | 3

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ID_GENERATOR")
    @TableGenerator(name = "ID_GENERATOR",
    table = "jpa_id_generators",
    pkColumnName = "PK_NAME",
    valueColumnName = "PK_VALUE",

    pkColumnValue = "CUSTOMER_ID",
    allocationSize = 100)*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //默认AUTO 使用数据库自己自增的策略
    public Integer getId() {
        return id;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Temporal(TemporalType.DATE)
    public Date getBirth() {
        return birth;
    }

    /**
     * 如果get方法没加注解，默认为@Basic
     *
     * @Basic 字段是个隐藏字段，一般都不用加他。因为@Entity里面的字段默认都相当于加上@Basic了，就是要在数据库生成。
     * 但是如果有个字段不需要在数据库生成。那么加上@Transient即可.
     * @return
     */
    @Basic
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 工具方法，不需要映射为数据表的一列
    @Transient
    public String getInfo(){
        return "lastName:"+lastName+",email:"+email;
    }

    //映射单向1-n的关联关系
    //使用@OneToMany 来映射 1-n的关系
    //使用@JoinColumn来映射外键列的名称
    //默认多的一方懒加载，我修改为非懒加载
    //cascade = {CascadeType.REMOVE} 该参数表示删除1的一端时，把多的一端也删除，默认是不删除的
    //mappedBy = "customer" 关联关系由Order那边去维护
    //注意：若在1的一端，@OneToMany中使用mappedBy属性（属性值为多的一方的成员变量字段），则@OneToMany端就不能再使用@JoinColumn属性了
    //@JoinColumn(name = "CUSTOMER_ID")
    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE},mappedBy = "customer")
    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", createdTime=" + createdTime +
                ", birth=" + birth +
                '}';
    }
}
