package example.zgc;

import java.util.ArrayList;
import java.util.List;

/**
 * 目前不建议用到生产环境,还处于试验阶段
 *
 * 支持TB级内存容量
 * GC暂停时间不超过10ms
 *
 * 和G1相比，应用吞吐能力不会下降超过15%
 * 初始只支持64位系统
 *
 * ZGC是一个并发，基于region，压缩性的垃圾收集器，只有root扫描阶段会STW，
 * 因为GC停顿时间不会随着堆的增长和存活对象增长而变长
 *
 * ZGC : avg 1.091ms  max:1.681
 * G1  : avg 156.806  max:543.846
 */

public class ZGCTest {
	// 开启方法: -XX:+UnlockExperimentalVMOptions -XX:+UseZGC
	public static void main(String[] args) {
		List<Garbage> list = new ArrayList<>();
		boolean flag = true;
		int count = 0;
		while (flag) {
			list.add(new Garbage());
			if (count++ % 500 == 0) {
				list.clear();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Garbage {
    private double d1 = 1;
    private double d2 = 2;

    //在当前对象被GC的时候，会调用一次这个方法
    @Override
    public void finalize() {
        System.out.println(this + " collecting");
    }
}
