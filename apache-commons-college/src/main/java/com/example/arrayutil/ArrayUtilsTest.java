package com.example.arrayutil;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

public class ArrayUtilsTest {


    /**
     * 数组工具
     */
    @Test
    public void _0() {
        int[] nums1 = {1, 2, 3, 4};

        //通过常量创建新数组
        int[] nums2 = ArrayUtils.EMPTY_INT_ARRAY;

        //输出数组,第二参数为数组为空时代替输出
        ArrayUtils.toString(nums1, "array is null");

        //克隆新数组
        int[] nums3 = ArrayUtils.clone(nums1);

        //截取数组
        int[] subarray = ArrayUtils.subarray(nums1, 1, 2);

        //判断两个数组长度是否相等
        boolean sameLength = ArrayUtils.isSameLength(nums1, nums2);

        //判断两个数组类型是否相等,注意int和Integer比较时不相等
        boolean sameType = ArrayUtils.isSameType(nums1, nums2);

        //反转数组
        ArrayUtils.reverse(nums1);

        //查找数组元素的位置
        ArrayUtils.indexesOf(nums3,5);

        //查找元素最后出现的位置
        int i = ArrayUtils.lastIndexOf(nums1, 4);

        //查找元素是否存在数组中
        ArrayUtils.contains(nums1,1);

        //将基本数组类型转换为包装类型
        Integer[] integers = ArrayUtils.toObject(nums1);

        //判断数组是否为空
        boolean empty = ArrayUtils.isEmpty(nums1);

        // 并集操作,合并数组
        ArrayUtils.addAll(nums1, nums2);

        // 删除指定位置元素,注意返回新数组,删除元素后面的元素会前移,保持数组有序
        ArrayUtils.remove(nums1, 5);

        // 删除数组中值为10的元素,以值计算不以下标
        ArrayUtils.removeElement(nums1, 10);

    }
}
