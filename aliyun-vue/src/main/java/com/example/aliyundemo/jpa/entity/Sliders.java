package com.example.aliyundemo.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sliders")
@Entity
public class Sliders implements Serializable {

    private Long id;
    private String url;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

