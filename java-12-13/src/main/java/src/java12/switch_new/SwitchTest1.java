package src.java12.switch_new;

import org.junit.Test;


public class SwitchTest1 {

    //java 12的新特性
    //一个case可以多个，还有lambda表达式
    @Test
    public void testSwitch1(){
        Fruit fruit = Fruit.APPLE;
        switch(fruit) {
            case PEAR -> System.out.println("4");
            case APPLE,GRAPE,MANGO -> System.out.println("5");
            case ORANGE,PAPAYA -> System.out.println("6");
            default -> throw new IllegalStateException("No Such Fruit:" + fruit);
        }
    }

    /**
     * java 12的新特性
     * switch 可以有返回值
     */
    @Test
    public void testSwitch2(){
        int numberOfLetters;
        Fruit fruit = Fruit.APPLE;
        numberOfLetters = switch(fruit) {
            case PEAR -> 4;
            case APPLE,GRAPE,MANGO -> 5;
            case ORANGE,PAPAYA -> 6;
            default -> throw new IllegalStateException("No Such Fruit:" + fruit);
        };
        System.out.println(numberOfLetters);
    }
}
