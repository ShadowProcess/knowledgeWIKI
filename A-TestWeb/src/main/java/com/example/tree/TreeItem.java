package com.example.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeItem {
    private String id;
    private String parentId;
    private String name;

    private List<TreeItem> children = new ArrayList<>();

    public TreeItem(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
