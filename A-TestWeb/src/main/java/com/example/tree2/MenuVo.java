package com.example.tree2;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    private Integer id;
    private Integer parentId;
    private String name;
    private String type;
    private List<MenuVo> children;

    public MenuVo(Integer id, Integer parentId, String name, String type) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
    }
}
