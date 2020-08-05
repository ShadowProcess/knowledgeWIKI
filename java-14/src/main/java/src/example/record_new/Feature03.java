package src.example.record_new;

import org.junit.Test;

import java.util.HashSet;

/**
 *  Record类型的演示
 */
public class Feature03 {
    @Test
    public void test1(){
        //测试构造器
        Person p1 = new Person("罗密欧",new Person("zhuliye",null));

        //测试toString()
        System.out.println(p1); //打印的跟我们重写toString方法是一样的，而不是原来的hash值

        //测试equals():
        Person p2 = new Person("罗密欧",new Person("zhuliye",null));
        System.out.println(p1.equals(p2)); //返回true，是按照内容比较的，而不是比较地址值

        //测试hashCode()和equals()
        HashSet<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2); //第二个添加不进去

        for (Person person : set) {
            System.out.println(person);
        }

        //测试name()和partner():类似于getName()和getPartner()
        System.out.println(p1.name());
        System.out.println(p1.partner());
    }

    @Test
    public void test2(){
        Person p1 = new Person("zhuyingtai");
        System.out.println(p1.getNameInUpperCase());

        Person.nation = "CHN";
        System.out.println(Person.showNation());
    }
}
