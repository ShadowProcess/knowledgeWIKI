package src.example.switch_new;

import org.junit.Test;

/**
 *  switch的新特性
 */
public class Feature04 {
    //jdk12之前的用法
    @Test
    public void test1() {

        Week day = Week.FRIDAY;
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
                System.out.println(1);
                break;
            case THURSDAY:
                System.out.println(2);
                break;
            case FRIDAY:
            case SATURDAY:
                System.out.println(3);
                break;
            case SUNDAY:
                System.out.println(4);
                break;
            default:
                throw new IllegalStateException("What day is today?" + day);
        }
    }

    //jdk12新特性：引用switch表达式
    @Test
    public void test2(){

        Week day = Week.FRIDAY;
        switch (day){
            case MONDAY,TUESDAY,WEDNESDAY -> System.out.println(1);
            case THURSDAY -> System.out.println(2);
            case FRIDAY,SATURDAY -> System.out.println(3);
            case SUNDAY -> System.out.println(4);
            default -> throw new IllegalStateException("What day is today?" + day);
        }

        //使用变量接收switch表达式的值
        int num = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY -> 1;
            case THURSDAY -> 2;
            case FRIDAY, SATURDAY -> 3;
            case SUNDAY -> 4;
            default -> throw new IllegalStateException("What day is today?" + day);
        };

        System.out.println(num);
    }

    //jdk13新特性：引用了yield关键字，用于返回指定的数据，结束switch结构
    @Test
    public void test3(){
        String x = "3";
        int num = switch (x){
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            default -> {
                System.out.println("default...");
                yield 4;
            }
        };

        System.out.println(x);
    }

    @Test
    public void test4(){
        String x = "3";
        int num = switch (x){
            case "1":yield 1;
            case "2":yield 2;
            case "3":yield 3;
            default: yield 4;
        };
        System.out.println(num);
    }
}

enum Week {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}