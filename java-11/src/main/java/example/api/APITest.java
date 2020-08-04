package example.api;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class APITest {

	@Test
	public void test3() {
		//不可变的时间  of好像有相同意义，都是不可变的
		LocalDate localDate = LocalDate.of(2019, 1, 21);

		//在添加重复元素时，不是无法添加，而是抛出重复元素异常 8是重复的
		//Set<Integer> set = Set.of(100, 50, 20, 30, 10, 8, 8);
		Set<Integer> set = Set.of(100, 50, 20, 30, 10, 8);
		System.out.println(set.getClass());
		
		Stream<Integer> stream = Stream.of(50, 20, 30, 70);
	}
	
	@Test
	public void test2() {
		int[] arr = {1, 9, 3, 2, 8};

		List<String> list1 = Arrays.asList("aa", "yyy", "zzz", "123");
		//list1.add("ppp"); //通过Arrays创建的集合，也是一个不可添加元素的集合，将会报错

		//不可变集合
		List<String> list2 = List.of("aa", "bbb", "cc", "DD");
		System.out.println(list2);
		
		//list2.add("yyy"); 将报错，不可变集合
		System.out.println(list2);
	}


	//集合中一些增强的API
	@Test
	public void test1() {
		List<String> list = new ArrayList<>(); //后边这个钻石符号，是可以类型推断出来的
		list.add("aa");
		list.add("bbb");
		list.add("cc");
		list.add("DD");
		System.out.println(list);
	}
}
