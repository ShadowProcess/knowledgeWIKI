package com.example.jpademo.jpa.entity.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "jpa_item")
@Entity
public class Item {
    private Integer id;
    private String iteName;
    private Set<Category> categories = new HashSet<>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "item_name")
    public String getIteName() {
        return iteName;
    }

    public void setIteName(String iteName) {
        this.iteName = iteName;
    }

    /**
     * 使用@JoinTable来映射中间表
     * 1. name 指向中间表的名字
     * 2. joinColumns 映射当前类所在的表在中间表中的外键
     * 2.1 name 指定外键列的列名
     * 2.2 referencedColumnName 指定外键列所关联当前表的哪一列
     *
     * 3. inverseJoinColumns 映射关联的类所在中间表的外键
     * 3.1 name 指定外键列的列名
     * 3.2 referencedColumnName 指定外键列所关联【关联的类】的哪一列
     */

    //name：中间表名
    //joinColumns = 【item_id：中间表的字段名】  【id：指向当前表的id】
    @JoinTable(name = "item_category",
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            //inverseJoinColumns = 【category_id：中间表的另外一个字段名】 【id：指向另外表的那个字段】
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
