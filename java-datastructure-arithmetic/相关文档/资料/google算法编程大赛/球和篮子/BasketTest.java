public class BasketTest {
	public static boolean Test1() {
		FillBaskets fb=new FillBaskets();
		int baskets=2;
		int capacity=20;
		int balls=2;
		int key=3;
		int result=fb.countWays(baskets,capacity,balls);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test2() {
		FillBaskets fb=new FillBaskets();
		int baskets=3;
		int capacity=20;
		int balls=1;
		int key=3;
		int result=fb.countWays(baskets,capacity,balls);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test3() {
		FillBaskets fb=new FillBaskets();
		int baskets=3;
		int capacity=20;
		int balls=2;
		int key=6;
		int result=fb.countWays(baskets,capacity,balls);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test4() {
		FillBaskets fb=new FillBaskets();
		int baskets=1;
		int capacity=5;
		int balls=10;
		int key=0;
		int result=fb.countWays(baskets,capacity,balls);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test5() {
		FillBaskets fb=new FillBaskets();
		int baskets=4;
		int capacity=5;
		int balls=10;
		int key=146;
		int result=fb.countWays(baskets,capacity,balls);
		System.out.println (result);
		return result==key;
	}
	public static void main(String[] args) {
		if (Test1()) {
			System.out.println ("Test1 Ok");
		}
		if (Test2()) {
			System.out.println ("Test2 Ok");
		}
		if (Test3()) {
			System.out.println ("Test3 Ok");
		}
		if (Test4()) {
			System.out.println ("Test4 Ok");
		}
		if (Test5()) {
			System.out.println ("Test5 Ok");
		}
	}
}