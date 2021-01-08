package com.example;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class DailyTest {


    @Test
    public void arrList(){
        List list = new ArrayList();
        System.out.println(list.size());
        //((ArrayList) list).elementData.length === 10   默认容量是10

        Stream.of(1,2,3,4,5,6,7,8,9,10,11).forEach(x->
                list.add(x)
        );
        //((ArrayList) list).elementData.length === 15   扩容后为15
        System.out.println(list.size());
    }



    @Test
    public void sTol(){
        String g5SpecialProductRecommend = "2021-01-04T00:00:00";
        LocalDateTime parse = LocalDateTime.parse(g5SpecialProductRecommend);
        System.out.println(parse);
    }

    @Test
    public void getLocalDateTime(){
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void getDay(){
        System.out.println(LocalDate.now().getDayOfMonth());
    }

    @Test
    public void lop() {
        String character = "";
        Stream.iterate(1, x -> x + 1)
                .limit(4)
                .forEach(y ->
                        sss(character, y)
                );
        System.out.println(character);
    }

    public void sss(String s, int i) {
        s = s += i;
        System.out.println(s);
    }

    @Test
    public void wer() {
        Person s = new Person();
        Stream.iterate(0, x -> x + 1).limit(4)
                .forEach(
                        x-> {
                            s.setName(x.toString());
                        }
                );
        System.out.println(s);
    }


    /**
     * 可以用来处理前端分页排序问题
     */
    @Test
    public void stream() {
        int pageNum = 0, pageSize = 0;
        Stream.of(1, 2, 3, 4, 5)
                .skip(pageNum * pageSize)
                .limit(pageSize).forEach(
                System.out::println
        );
    }


    /**
     * （1）总页数公式：totalRecord是总记录数；pageSize是一页分多少条记录
     * int totalPageNum = (totalRecord +pageSize - 1) / pageSize;
     * <p>
     * 特殊情况：总记录数 和 每页条数一样
     */
    @Test
    public void page() {
        int totalCount = 1; //数据总量
        int pageSize = 1;   //每页条数

//        if (totalCount == pageSize) {
//            System.out.println("总页数:1");
//            return;
//        }
        int pageCount = ((totalCount - 1) / pageSize) + 1; //总页数 (固定分页公式)
        System.out.println("总页数：" + pageCount);
    }


    @Test
    public void digest() {
        System.out.println(1 / 3);
        //System.out.println(5%3); 2
    }


    @Test
    public void test() {
        String unicode = "\\u4f73\\u3002";
        String[] split = unicode.split("\\\\u");
        for (String s1 : split) {
            System.out.println(s1);
        }
        System.out.println(Arrays.toString(split));
        StringBuilder chinese = new StringBuilder();
        for (String s : split) {
            if (StringUtils.isBlank(s)) continue;
            chinese.append((char) Integer.valueOf(s, 16).intValue());
        }
        System.out.println(chinese.toString());
    }

    @Test
    public void te() {
        String str2 = "";
        String[] split2 = str2.split(",");
        for (String s1 : split2) {
            boolean blank = StringUtils.isBlank(s1);
            System.out.println(blank);
            System.out.println(s1);
        }
        System.out.println(split2.length); //返回结果1；值得思考
    }

    @Test
    public void tt() {
        String str3 = ",";
        String[] split3 = str3.split(",");
        for (String s1 : split3) {
            System.out.println(s1);
        }
        System.out.println(split3.length);
        //这个结果是0,但部分人会认为结果是1,部分人会认为结果是2.
        //这个又为什么是0,我也会在后面说
    }

    @Test
    public void kjl() {
        String unicode = "\"\\u4f73\\u3002\"";
        String s = unicode.replaceAll("\"", "");
        System.out.println(s);
    }


    @Test
    public void split() {
        String line = "\\u4f73\\u3002";
        List<String> split2 = Splitter.on("\\u").splitToList(line);
        for (String s1 : split2) {
            System.out.println(s1);
        }
    }

    @Test
    public void jhj() {
        String yyyyMMddHHmm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        long l = Long.parseLong(yyyyMMddHHmm);
        System.out.println(l);
    }

    @Test
    public void hashSet() {
        Set<String> set = new HashSet();
        set.add("12");
        set.add(null);
        set.forEach(System.out::println);
    }

    String[] s = new String[]{};

    @Test
    public void arr() {
        //如果s不初始化，将抛出空指针
        System.out.println(Arrays.asList(s));
    }

    @Test
    public void arr1() {
        //如果s不初始化，将抛出空指针
        for (String s1 : s) {
            System.out.println(s1.toUpperCase());
        }
    }


    @Test
    public void _0() {
        Object o = Optional.ofNullable(null).isPresent();
    }

    @Test
    public void _1() {
        boolean ss = StringUtils.equals("-1", "ss");
        System.out.println(ss);
    }

    public static <T, E> void s(T s, E y) {
    }

    @Test
    public void _0_() {
        s("ss", "ff");
    }


    //仅显示姓名最后一位，其余用*代替
    @Test
    public void GFDS() {
        String name = "字";

        int i = name.length() - 1;
        String substring = name.substring(name.length() - 1);
        String mi = "";
        for (int j = 0; j < i; j++) {
            mi += "*";
        }
        System.out.println(mi + substring);
    }

    @Test
    public void ii() {
        int monthValue = LocalDate.now().getMonthValue();
        int day = LocalDate.now().getDayOfMonth();
        System.out.println(String.format("%d月%d日", monthValue, day));
    }

    @Test
    public void OO() {
        LocalDateTime openStart = LocalDateTime.of(2020, 8, 6, 0, 0, 0);
        LocalDateTime openEnd = LocalDateTime.of(2020, 8, 8, 0, 0, 0);
        Duration between = Duration.between(openStart, openEnd);
        System.out.println(between.toDays());

        for (int i = 0; i < between.toDays(); i++) {
            System.out.println(openStart.plusDays(i).toString());
        }
    }

    @Test
    public void q() {
        String s = "123";
        String substring = s.substring(s.length() - 1);
        System.out.println(substring);
    }


    /**
     * @param luckyDay 开奖日 1-31
     * @return 距离开奖还有几天
     */
    public static int lucky(int luckyDay) {
        if (luckyDay < 1 || luckyDay > 31)
            throw new IllegalArgumentException("illegal argument " + luckyDay + "; closed interval:[1,31]");
        LocalDate now = LocalDate.now();
        int intervalDays = luckyDay - now.getDayOfMonth();
        if (intervalDays >= 0) return intervalDays;

        int intervalLastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth())
                .minusDays(now.getDayOfMonth())
                .getDayOfMonth();
        return intervalLastDayOfMonth + luckyDay;
    }

    @Test
    public void TT() {
        int qq = lucky(8);
        System.out.println(qq);
    }


    @Test
    public void TT1() {
        System.out.println(YearMonth.now());
        System.out.println(Year.now());
        System.out.println(Month.APRIL);
        System.out.println("Don`t match");
        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
    }

    @Test
    public void WW() {
        Stream.of(1, 2).limit(3).forEach(System.out::println);
    }

    @Test
    public void ff() {
        //0.1*3的结果是浮点型，也就是0.30000000000000004，
        //但是有的计算结果不是的，比如4*0.1结果就是0.4；这个是《二进制浮点数算法》的计算 原因，不深究，记一下就行
        System.out.println(0.1 * 3 == 0.3); //false
    }

    @Test
    public void stringTest() {
        String a = "a", b = "a";
        System.out.println(a == b); //true   String比较尽量用equals不要用==
    }

    @Test
    public void math() {
        long round = Math.round(-1.5);
        System.out.println(round); // -1
    }

    //可以声明volatile数组
    volatile int[] i;


    //public boolean equals(Object obj) {
    //    return (this == obj);
    //}
    // == 比较的是内存地址

    //public native int hashCode();

//    HashCode 是为了集合操作快速，而根据一定规则而设计的散列码(根据内存地址生成的，为了方便比较和插入数据)，用于 HashMap,HashSet,HashTable.
//    hashCode()方法返回的就是一个数值，从方法的名称上就可以看出，其目的是生成一个hash码。hash码的主要用途就是在对对象进行散列的时候作为key输入，
//    据此很容易推断出，我们需要每个对象的hash码尽可能不同，这样才能保证散列的存取性能。事实上，Object类提供的默认实现确实保证每个对象的hash码不同
//    （在对象的内存地址基础上经过特定算法返回一个hash码）。Java采用了哈希表的原理。哈希（Hash）实际上是个人名，由于他提出一哈希算法的概念，
//    所以就以他的名字命名了。 哈希算法也称为散列算法，是将数据依特定算法直接指定到一个地址上。初学者可以这样理解，
//    hashCode方法实际上返回的就是对象存储的物理地址（实际可能并不是）。
//
//    散列函数,散列算法,哈希函数。
//    是一种从任何一种数据中创建小的数字“指纹”的方法。
//    散列函数将任意长度的二进制值映射为较短的固定长度的二进制值，这个小的二进制值称为哈希值。
//    好的散列函数在输入域中很少出现散列冲突。

//    1、如果两个对象equals，Java运行时环境会认为他们的hashcode一定相等。
//    2、如果两个对象不equals，他们的hashcode有可能相等。
//    3、如果两个对象hashcode相等，他们不一定equals。
//    4、如果两个对象hashcode不相等，他们一定不equals。


    @Test
    public void StringS() {
        String s = new String("s"); //s指向堆
        String s1 = s.intern();            //s1指向常量池
        System.out.println(s == s1);
        List list1 = new ArrayList();
        List list = new Vector();
        list.add(1);
        list.add(2);
        System.out.println(list);

//        关于ArrayList和Vector区别如下：
//
//        ArrayList在内存不够时默认是扩展1.5倍，Vector是默认扩展1倍。
//        Vector提供indexOf(obj, start)接口，ArrayList没有。
//        Vector属于线程安全级别的，但是大多数情况下不使用Vector，因为线程安全需要更大的系统开销
    }

    @Test
    public void lo() {
        LocalDateTime effect = LocalDateTime.parse("2020-09-08 07:00:47.0", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        System.out.println(effect);

        YearMonth yearMonth = YearMonth.now();
        YearMonth effect1 = YearMonth.from(LocalDateTime.now());
        System.out.println(effect1);

        LocalDateTime start = LocalDate.of(2020, 9, 11).atTime(LocalTime.MIN);
        LocalDateTime end = LocalDate.of(2020, 9, 20).atTime(LocalTime.MAX);
        System.out.println(start);
        System.out.println(end);
    }


    @Test
    public void settt() {
        final ZoneId zoneId = ZoneId.systemDefault();
        final Clock secondClock = Clock.tickSeconds(zoneId);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        final String today = LocalDateTime.now(secondClock).format(formatter);
        System.out.println(today);
    }

    @Test
    public void ss() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

}
