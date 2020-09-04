//package src.example.instance_of;
//
//import org.junit.Test;
//
///**
// * 1. JEP 305：instanceof的模式匹配（预览）
// */
//public class Feature01 {
//    @Test
//    public void test1(){
//        Object obj = new String("hello,Java14");
//        obj = null;//在使用null 匹配instanceof 时，返回都是false.
//        if(obj instanceof String){
//            String str = (String) obj;
//            System.out.println(str.contains("Java"));
//        }else{
//            System.out.println("非String类型");
//        }
//
//        //举例1：
//        if(obj instanceof String str){ //新特性：省去了强制类型转换的过程
//            System.out.println(str.contains("Java"));
//        }else{
//            System.out.println("非String类型");
//        }
//    }
//}
//
//// 举例2
//class InstanceOf{
//    String str = "abc";
//
//    public void test(Object obj){
//
//        if(obj instanceof String str){//此时的str的作用域仅限于if结构内。
//            System.out.println(str.toUpperCase());
//        }else{
//            System.out.println(str.toLowerCase());
//        }
//    }
//}
//
////举例3：
//class Monitor{
//    private String model;
//    private double price;
//
////    public boolean equals(Object o){
////        if(o instanceof Monitor other){
////            if(model.equals(other.model) && price == other.price){
////                return true;
////            }
////        }
////        return false;
////    }
//
//
//    public boolean equals(Object o){
//        return o instanceof Monitor other && model.equals(other.model) && price == other.price;
//    }
//}