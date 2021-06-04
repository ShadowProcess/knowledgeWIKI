package com.example.tree1;


import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeUtil {
    private volatile static TreeUtil INSTANCE;

    //私有构造
    private TreeUtil() {
    }

    //获取树形工具单例(DCL单例)
    public static TreeUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (TreeUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TreeUtil();
                }
            }
        }
        return INSTANCE;
    }


    public TreeItem enquireTree(List<TreeItem> treeItemList) {
        //过滤空对象
        if (CollectionUtils.isEmpty(treeItemList)) return null;
        List<TreeItem> treeItems = treeItemList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        //构建id和treeItem对应关系
        HashMap<String, TreeItem> itemMap = new HashMap<>();
        treeItems.forEach(x -> itemMap.put(x.getId(), x));

        //声明一个变量存放根节点
        TreeItem root = null;

        //数据封装
        for (TreeItem treeItem : treeItems) {
            String pid = treeItem.getParentId();

            //1.说明该节点为根节点
            if (StringUtils.isEmpty(pid) || pid == "0") {
                root = treeItem;
                continue;
            }

            //2.该节点不是根节点，得到该节点的父节点，把自己添加到父节点下。
            TreeItem parent = itemMap.get(pid);
            parent.getChildren()
                    .add(treeItem);
        }
        return root;
    }


    public static void main(String[] args) {

        List<TreeItem> treeItemList = Arrays.asList(
                new TreeItem("1", "0", "中国"),
                new TreeItem("10", "1", "河北"),
                new TreeItem("100", "10", "北京"),
                new TreeItem("110", "10", "天津"),
                new TreeItem("20", "1", "河南"),
                new TreeItem("200", "20", "郑州"),
                new TreeItem("210", "20", "洛阳"),
                new TreeItem("220", "20", "许昌"),
                new TreeItem("221", "220", "禹州")
        );

        TreeItem treeItem = TreeUtil.getInstance().enquireTree(treeItemList);

        System.out.println(JSONObject.toJSONString(treeItem));

    }

}
