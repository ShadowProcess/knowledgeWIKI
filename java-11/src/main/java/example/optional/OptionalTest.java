package example.optional;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {
	
	@Test
	public void testName() throws Exception {
		// of方法如果传入的参数是null，会抛出空指针异常
		//Optional<String> optional = Optional.of(null);


		// ofNullable可以传入null
		Optional<Object> optional = Optional.ofNullable(null);
		Object object = optional.orElse("abc"); //如果容器中为空，就返回你指定的"abc"
		System.out.println(object);
		
		Object object2 = optional.orElseThrow(); //如果容器为空, 就抛出NoSuchElementException异常
		System.out.println(object2);
	}
}
