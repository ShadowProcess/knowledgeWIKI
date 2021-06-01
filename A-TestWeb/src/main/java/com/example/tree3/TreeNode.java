package com.example.tree3;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode {

    private Integer id;
    private Integer parentId;
    private String name;
    private String type;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(Integer id, Integer parentId, String name, String type) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.type = type;
    }
}
