###Compare原理

public int compareTo(Student o){
    return this.age - o.age; //比较年龄(年龄的升序)
} 

为什么return this.age - o.age是升序？ 困扰很久的痛点,终于想明白，想明白就很好记忆了

###第一步：首先要知道 Collections.sort()方法进行排序的时候，sort里面默认是升序排序。这里一定要记住了。
第二步：compare函数的返回值-1,1,0是什么个意思?

 返回值-1，表示左边的数比右边的数小,左右的数不进行交换
 返回值0，表示左边的数等于右边的数，左右的数不进行交换
 返回值为1，表示左边的数比右边的数大,左右的数进行交换（不进行交换的话，就没办法维持升序了）
 
 ----------
 上面的例子中this.age可以理解成左边的数，o.age可以理解成右边的数。
 
 this.age-o.age>0 说明左边的数比右边的数大，return this.age-o.age 返回的是一个正数，就进行左右交换，所以最终输出是升序。
 
 this.age-o.age<0 说明左边的数比右边的数小，return this.age-o.age 返回的是一个负数，不用进行交换，所以最终输出是升序。
 
 
 ===============
 import java.util.*;
  
  
 class DescType implements Comparator
 {
     public int compare(Object o1, Object o2) 
     {
         Integer i = (Integer)o1;
         Integer j = (Integer)o2;
          
         return (i<j?1:(i==j?0:-1)); //右边数比左边数大，需要交换位置
     }
 }
  
 public class Hello
 {
     public static void main(String[] args)
     {
         Integer a[] = {3, 1, 2};
         Arrays.sort(a, new DescType());
          
         for(int i:a)
             System.out.println(i);
     }
 }