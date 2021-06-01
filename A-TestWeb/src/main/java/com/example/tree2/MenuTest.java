package com.example.tree2;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 该算法支持顶级节点可以是多个,或者一个.
 */

public class MenuTest {

    public static void main(String[] args) {
        List<MenuVo> voList = new ArrayList<>();
        MenuVo vo1 = new MenuVo(1, 0, "菜单1", "one");
        MenuVo vo2 = new MenuVo(2, 1, "菜单2", "two");
        MenuVo vo3 = new MenuVo(3, 2, "菜单2-2", "two-two");
        MenuVo vo4 = new MenuVo(4, 1, "菜单3", "three");
        MenuVo vo5 = new MenuVo(5, 4, "菜单3-3", "three-three");
        MenuVo vo6 = new MenuVo(6, 0, "菜单4", "six");
        voList.add(vo1);
        voList.add(vo2);
        voList.add(vo3);
        voList.add(vo4);
        voList.add(vo5);
        voList.add(vo6);

        //递归父子节点树形关系
        List<MenuVo> resultList = MenuTest.parseMenuTree(voList);

        System.out.println(JSONObject.toJSONString(resultList));

        /**
         * 如果有两个id相同的值?会出现以下原因，但是正常数据不会出现两个id相同的.
         *
         * 原因：后台传过去的json数据用了阿里的fastjson转换，但是解析list中引用的数据时，jvm会自动将其处理为“循环引用”，
         * 因此，也就出现了问题 {"$ref": "$[0].children[0].children[1].children[0]"}，数据以引用的方式传给前台，前台却无法解析到那段引用的数据。
         *
         * 循环引用就是：当一个对象包含另一个对象时，fastjson就会把该对象解析成引用。
         * 用这种转换方式，把list替换成你要转换的数据就可以了。
         *
         * TODO 解决fastjson循环依赖
         * System.out.println(JSONObject.toJSONString(resultList, SerializerFeature.DisableCircularReferenceDetect));
         */
    }

    /**
     * @param list 数据库里面获取到的全量菜单列表
     * @return
     */
    public static List<MenuVo> parseMenuTree(List<MenuVo> list) {
        List<MenuVo> result = new ArrayList<MenuVo>();

        // 1、获取第一级节点
        for (MenuVo content : list) {
            if (0 == content.getParentId()) {
                result.add(content);
            }
        }

        // 2、递归获取子节点
        for (MenuVo contentVo : result) {
            recursiveMenuTree(contentVo, list);
        }
        return result;
    }

    /**
     * 获取节点及其子节点返回对象
     *
     * @param parent
     * @param list
     * @return
     */
    public static void recursiveMenuTree(MenuVo parent, List<MenuVo> list) {
        List<MenuVo> childList = new ArrayList<>();
        for (MenuVo child : list) {
            if (Objects.equals(parent.getId(), child.getParentId())) {
                childList.add(child);
                parent.setChildren(childList);
                recursiveMenuTree(child, list);
            }
        }
    }
}
