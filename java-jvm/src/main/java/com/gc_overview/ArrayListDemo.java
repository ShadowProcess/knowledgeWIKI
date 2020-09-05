package com.gc_overview;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        Integer capacity = getCapacity(list1);// 获取容量
        int size = list1.size();
        System.out.println("list1的容量:" + capacity);
        System.out.println("list1的大小:" + size);
        System.out.println("----------------------------");

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        capacity = getCapacity(list2);// 获取容量,arraylist初始化容量是10
        size = list2.size();
        System.out.println("list2的容量:" + capacity);
        System.out.println("list2的大小:" + size);
        System.out.println("----------------------------");

        ArrayList<Integer> list3 = new ArrayList<>();

        capacity = getCapacity(list3);// 获取容量,arraylist初始化容量是10
        for (int i = 0; i < 10; i++) {
            list3.add(i);
        }
        capacity = getCapacity(list3);
        size = list3.size();
        System.out.println("list3的容量:" + capacity);
        System.out.println("list3的大小:" + size);
        System.out.println("----------------------------");

        ArrayList<Integer> list4 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            list4.add(i);
        }
        capacity = getCapacity(list4);// 获取容量
        size = list4.size();
        System.out.println("list4的容量:" + capacity);
        System.out.println("list4的大小:" + size);

    }

    // 获取list容量
    public static Integer getCapacity(ArrayList list) {
        Integer length = null;
        Class clazz = list.getClass();
        Field field;
        try {
            field = clazz.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] object = (Object[]) field.get(list);
            length = object.length;
            return length;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return length;
    }
}
