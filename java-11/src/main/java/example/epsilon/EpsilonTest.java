package example.epsilon;

import java.util.ArrayList;
import java.util.List;

class Garbage {
	private double d1 = 1;
	private double d2 = 2;
	
	//在当前对象被GC的时候，会调用一次这个方法
	@Override
	public void finalize() {
		System.out.println(this + " collecting");
	}
}


/**
 * 新的Epsilon垃圾收集器 [又称无操作垃圾回收器]
 * 用途：
 * 1.性能测试
 * 2.内存压力测试
 * 3.VM接口测试
 * 4.延迟&吞吐改进
 */
public class EpsilonTest {
	
	// 开启方法: -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
	public static void main(String[] args) {
		List<Garbage> list = new ArrayList<>();
		boolean flag = true;
		int count = 0;
		while (flag) {
			list.add(new Garbage());
			if (count++ == 500) {
				list.clear();
			}
		}
	}
}
