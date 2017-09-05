package com.example.section;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import org.junit.Test;

/**
 * 开区间与闭区间
 * <p>
 * (0,1) 开区间就是端点不能取 这里就是 0 和 1 不能取
 * [0,1] 闭区间就是端点可以取 这里就是 0 和 1 可以取
 */

public class SectionDemo {

    //区间
    @Test
    public void _0() {
        Range<Integer> integerRange = Range.downTo(4, BoundType.OPEN);
        System.out.println(integerRange); //(4..+∞)

        Range<Integer> range = Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN);
        System.out.println(range); //[1..4),等同于Range.closedOpen(1, 4)
    }

    @Test
    public void _1() {
        boolean contains = Range.closed(1, 3).contains(2);
        boolean contains1 = Range.closed(1, 3).contains(4);
        System.out.println(contains); //true
        System.out.println(contains1);//false

        boolean contains2 = Range.lessThan(5).contains(5);
        System.out.println(contains2);//false

        //Ints是int的工具类
        //Longs是long的工具类
        Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)); //true

        boolean empty = Range.closedOpen(4, 4).isEmpty();
        System.out.println(empty); //true

        Range.openClosed(4, 4).isEmpty(); //true
    }


    //判断区间是否相连
    @Test
    public void _2() {
        boolean connected = Range.closed(3, 5).isConnected(Range.open(5, 10));
        System.out.println(connected); //true

        boolean connected1 = Range.open(3, 5).isConnected(Range.open(5, 10));
        System.out.println(connected1);//false 因为两个区间有重叠5
    }


    //交集[intersection]
    /**
     * 返回两个区间的交集：既包含于第一个区间，又包含于另一个区间的最大区间。
     * 当且仅当两个区间是相连的，它们才有交集。如果两个区间没有交集，该方法将抛出IllegalArgumentException。
     */
    @Test
    public void _3(){
        Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
        Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
        Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
        Range.open(3, 5).intersection(Range.open(5, 10)); // throws IAE
        Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IAE
    }

    //跨区间
    @Test
    public void _4(){
        Range.closed(3, 5).span(Range.open(5, 10)); // returns [3, 10)
        Range.closed(0, 9).span(Range.closed(3, 4)); // returns [0, 9]
        Range.closed(0, 5).span(Range.closed(3, 9)); // returns [0, 9]
        Range.open(3, 5).span(Range.open(5, 10)); // returns (3, 10)
        Range.closed(1, 5).span(Range.closed(6, 10)); // returns [1, 10]
    }

    @Test
    public void _5(){
        boolean contains = Range.open(0, 8000).contains(7999);
        System.out.println(contains);

        boolean contains1 = Range.downTo(8000, BoundType.CLOSED).contains(7999);
        System.out.println(contains1);


    }
}
