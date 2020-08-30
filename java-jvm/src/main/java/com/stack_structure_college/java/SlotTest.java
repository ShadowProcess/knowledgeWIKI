package com.stack_structure_college.java;

/**
 * 参数值的存放总是在局部变量数组的index0开始，到数组长度-1的索引结束
 *
 * 局部变量表，最基本的存储的单元是Slot（变量槽）
 *
 * 局部变量表中存放编译期可知的各种基本数据类型(8种)，引用类型(reference),returnAddress类型
 *
 * 在局部变量表里，32位以内的类型只占用一个Slot（包括returnAddress类型），64位的类型（long和double）占用两个slot
 *
 * byte short char在存储之前被转换为int,boolean也被转换为int， 0表示false，非0表示true
 *
 * long和double 则占据两个Slot
 */

public class SlotTest {
    public void localVarl() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localVar2() {
        {
            int a = 0;
            System.out.println(a);
        }
        //此时的b就会复用a的槽位
        int b = 0;
    }
}
