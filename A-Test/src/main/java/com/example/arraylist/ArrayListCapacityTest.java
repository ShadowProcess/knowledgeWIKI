package com.example.arraylist;


import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 通过源码中 grow方法分析
 *
 * ArrayList初始化容量为0
 * 在add的时候初始化容量为10
 *
 *  结论
 * ArrayList的初始化容量已经变了，不再是以前的10了，而是初始化为0，等到第一次add的时候再初始化为10。
 *
 * 做这样的改动，就是延迟初始化ArrayList的实际容量，应该是考虑到空间的问题，如果一开始就初始化为10，
 * 这个大小为10的数组中就全部是存的null，如果数量多了，这个也是很大的空间。应该是这样的原因。
 *
 */

public class ArrayListCapacityTest {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        System.out.println("capacity: " + getCapacity(arrayList) + " size: " + arrayList.size());

        arrayList.add("test");
        System.out.println("capacity: " + getCapacity(arrayList) + " size: " + arrayList.size());



        System.out.println("===============验证扩容机制还是1.5倍==============================");
        for (int i = 0; i < 10; i++) {
            arrayList.add("test");
        }
        System.out.println("capacity: " + getCapacity(arrayList) + " size: " + arrayList.size());
        System.out.println("=============================================");


        arrayList = new ArrayList(11);
        System.out.println("capacity: " + getCapacity(arrayList) + " size: " + arrayList.size());
    }

    public static int getCapacity(ArrayList arrayList) {
        try {
            Field elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
            return ((Object[]) elementDataField.get(arrayList)).length;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

