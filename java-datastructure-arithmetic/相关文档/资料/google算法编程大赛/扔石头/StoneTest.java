public class StoneTest {
	public boolean Test1() {
		SkipStones ss=new SkipStones();
		String water="..X.....X...";
		int result=11;
		return result==ss.maxDistance(water);
	}
	public boolean Test2() {
		SkipStones ss=new SkipStones();
		String water="...X...";
		int result=3;
		return result==ss.maxDistance(water);
	}
	public boolean Test3() {
		SkipStones ss=new SkipStones();
		String water="....X....X...XXXX.X.....";
		int result=22;
		return result==ss.maxDistance(water);
	}
	public boolean Test4() {
		SkipStones ss=new SkipStones();
		String water="XXXXXXX.XXX.X..";
		int result=15;
		return result==ss.maxDistance(water);
	}
	public static void main(String[] args) {
		StoneTest gt=new StoneTest();
		if (gt.Test1())
			System.out.println ("Test1 ok");
		if (gt.Test2())
			System.out.println ("Test2 ok");
		if (gt.Test3())
			System.out.println ("Test3 ok");
		if (gt.Test4())
			System.out.println ("Test4 ok");
    }
}