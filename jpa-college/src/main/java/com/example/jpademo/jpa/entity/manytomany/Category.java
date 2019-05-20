package com.example.jpademo.jpa.entity.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "jpa_category")
@Entity
public class Category {
    private Integer id;
    private String categoryName;
    private Set<Item> items = new HashSet<>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @ManyToMany(mappedBy = "categories")
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
