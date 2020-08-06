package com.example.java.string;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * 回顾字符串格式化
 * <p>
 * String类的format()方法用于创建格式化的字符串以及连接多个字符串对象。熟悉C语言的同学应该记得C语言的printf()方法，
 * 两者有类似之处。format()方法有两种重载形式。
 * <p>
 * format(String format, Object... args) 新字符串使用本地语言环境，制定字符串格式和参数生成格式化的新字符串。
 * format(Locale locale, String format, Object... args) 使用指定的语言环境，制定字符串格式和参数生成格式化的字符串。
 */
public class StringFormat {

    /**
     * %s   字符串类型   ("mingrisoft")
     * %c   字符类型     ('m')
     * %b   布尔类型      (true)
     * %d   整数类型（十进制）
     * %x   整数类型（十六进制）
     * %o   整数类型（八进制）
     * %f   浮点类型
     * %a   十六进制浮点类型
     * %e   指数类型
     * %g   通用浮点类型（f和e类型中较短的）
     * %h   散列码
     * %%   百分比类型
     * %n   换行符
     * %tx  日期与时间类型（x代表不同的日期与时间转换符
     */
    @Test
    public void _0() {
        String str = String.format("Hi,%s", "王力");
        System.out.println(str);
        str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3 > 7);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');

//        Hi,王力
//        Hi,王南:王力.王张
//        字母a的大写是：A
//        3>7的结果是：false
//        100的一半是：50
//        100的16进制数是：64
//        100的8进制数是：144
//        50元的书打8.5折扣是：42.500000 元
//        上面价格的16进制数是：0x1.54p5
//        上面价格的指数表示：4.250000e+01
//        上面价格的指数和浮点数结果的长度较短的是：42.5000
//        上面的折扣是85%
//        字母A的散列码是：41
    }

    @Test
    public void __() {
        String str = null;
        //$使用
        str = String.format("格式参数$的使用：%1$d,%2$s", 99, "abc");
        System.out.println(str);
        //+使用
        System.out.printf("显示正负数的符号：%+d与%d%n", 99, -99);
        //补0使用
        System.out.printf("最牛的编号是：%03d%n", 7);
        //空格使用
        System.out.printf("Tab键的效果是：% 8d%n", 7);
        //,使用
        System.out.printf("整数分组的效果是：%,d%n", 9989997);
        //空格和小数点后面个数
        System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);

//        格式参数$的使用：99,abc
//        显示正负数的符号：+99与-99
//        最牛的编号是：007
//        Tab键的效果是：       7
//        整数分组的效果是：9,989,997
//        一本书的价格是：                                          49.80000元
    }


    /**
     * 格式化日期： %t开头  %n是换行
     */
    @Test
    public void ___() {
        LocalDate now = LocalDate.now();
        String format = String.format("%tF", now);
        System.out.printf("全部日期和时间信息：%tc%n", now);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n", now);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n", now);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", now);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", now);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR", now);

        Date date=new Date();
        //b的使用，月份简称
        String str=String.format(Locale.US,"英文月份简称：%tb",date);
        System.out.println(str);
        System.out.printf("本地月份简称：%tb%n",date);
        //B的使用，月份全称
        str=String.format(Locale.US,"英文月份全称：%tB",date);
        System.out.println(str);
        System.out.printf("本地月份全称：%tB%n",date);
        //a的使用，星期简称
        str=String.format(Locale.US,"英文星期的简称：%ta",date);
        System.out.println(str);
        //A的使用，星期全称
        System.out.printf("本地星期的简称：%tA%n",date);
        //C的使用，年前两位
        System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",date);
        //y的使用，年后两位
        System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",date);
        //j的使用，一年的天数
        System.out.printf("一年中的天数（即年的第几天）：%tj%n",date);
        //m的使用，月份
        System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",date);
        //d的使用，日（二位，不够补零）
        System.out.printf("两位数字的日（不足两位前面补0）：%td%n",date);
        //e的使用，日（一位不补零）
        System.out.printf("月份的日（前面不补0）：%te",date);

        //H的使用
        System.out.printf("2位数字24时制的小时（不足2位前面补0）:%tH%n", date);
        //I的使用
        System.out.printf("2位数字12时制的小时（不足2位前面补0）:%tI%n", date);
        //k的使用
        System.out.printf("2位数字24时制的小时（前面不补0）:%tk%n", date);
        //l的使用
        System.out.printf("2位数字12时制的小时（前面不补0）:%tl%n", date);
        //M的使用
        System.out.printf("2位数字的分钟（不足2位前面补0）:%tM%n", date);
        //S的使用
        System.out.printf("2位数字的秒（不足2位前面补0）:%tS%n", date);
        //L的使用
        System.out.printf("3位数字的毫秒（不足3位前面补0）:%tL%n", date);
        //N的使用
        System.out.printf("9位数字的毫秒数（不足9位前面补0）:%tN%n", date);
        //p的使用
        String str1 = String.format(Locale.US, "小写字母的上午或下午标记(英)：%tp", date);
        System.out.println(str1);
        System.out.printf("小写字母的上午或下午标记（中）：%tp%n", date);
        //z的使用
        System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n", date);
        //Z的使用
        System.out.printf("时区缩写字符串:%tZ%n", date);
        //s的使用
        System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n", date);
        //Q的使用
        System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ%n", date);
    }

}
