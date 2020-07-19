package test.java.com.example.java8stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Java8streamApplicationTests {

    @Test
    public void contextLoads() {

        Stream.of("one", "two", "three", "four")
              .filter(e -> e.length() > 3)
              .peek(e -> System.out.println("Filtered value: " + e))
              .map(String::toUpperCase)
              .peek(e -> System.out.println("Mapped value: " + e))
              .collect(Collectors.toList());

//        Filtered value: three
//        Mapped value: THREE
//        Filtered value: four
//        Mapped value: FOUR

        //说明了流的元素，是一个一个经过该流的，而不是同时经过
    }


    @Test
    public void test1() {
        Stream.of("one", "two", "three", "four")
              .map(String::toUpperCase)
              .peek(it -> System.out.println(it))
              .collect(Collectors.toList());

//        ONE
//        TWO
//        THREE
//        FOUR
    }

    @Test
    public void test2() {
        Stream.of("one", "two", "three", "four")
              .filter(it -> it.equals("one"))
              .peek(it -> System.out.println(it))
              .collect(Collectors.toList());

        //  one
    }

    @Test
    public void test3(){
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6);
        List<Integer> list3 = Arrays.asList(7,8,9);

        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        List<Integer> list = listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
        //List<Integer> list = listOfLists.stream().flatMap(it -> it.stream()).collect(Collectors.toList());

        System.out.println(list);
    }

    //[1, 2, 3, 4, 5, 6, 7, 8, 9]

}
