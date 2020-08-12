import java.util.*;
public class FillBaskets {
	int count=0;
	int baskets,capacity,balls;
	int[] bas;
	public int countWays(int baskets, int capacity, int balls) {
		if (baskets*capacity<balls)
			return 0;
		this.baskets=baskets;
		this.balls=balls;
		if (balls<capacity)
			capacity=balls;
		this.capacity=capacity;
		bas=new int[baskets];
		
		//从第0个篮子开始放
		putBalls(0);
		return count;
	}
	
	//
	void putBalls(int n) {
		
		//检测是不是到达最后篮子
		if (n==baskets) {
			if (getSumBalls()==balls)
				count++;
			return;
		}
		
		//给第n个篮子放球,并且从0->capacity
		//一次轮循的放入
		for (int i=0;i<=capacity;i++) {
			bas[n]=i;
			putBalls(n+1);
		}
	}
	//得到当前篮子里面所有的球
	int getSumBalls() {
		int sum=0;
		for (int i=0;i<baskets;i++)
			sum+=bas[i];
		return sum;
	}
}