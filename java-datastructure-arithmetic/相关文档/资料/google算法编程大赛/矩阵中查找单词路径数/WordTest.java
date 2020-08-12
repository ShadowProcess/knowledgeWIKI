public class WordTest {
	public static boolean Test1() {
		WordPath wp=new WordPath();
		String[] grid={"ABC",
					   "FED",
					   "GHI"};
		String find="ABCDEFGHI";
		int key=1;
		int result=wp.countPaths(grid,find);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test2() {
		WordPath wp=new WordPath();
		String[] grid={"ABC",
					   "FED",
					   "GAI"};
		String find="ABCDEA";
		int key=2;
		int result=wp.countPaths(grid,find);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test3() {
		WordPath wp=new WordPath();
		String[] grid={"ABC",
					   "DEF",
					   "GHI"};
		String find="ABCD";
		int key=0;
		int result=wp.countPaths(grid,find);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test4() {
		WordPath wp=new WordPath();
		String[] grid={"AA",
                       "AA"};
		String find="AAAA";
		int key=108;
		int result=wp.countPaths(grid,find);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test5() {
		WordPath wp=new WordPath();
		String[] grid={"ABABA",
					   "BABAB",
					   "ABABA",
					   "BABAB",
					   "ABABA"};
		String find="ABABABBA";
		int key=56448;
		int result=wp.countPaths(grid,find);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test6() {
		WordPath wp=new WordPath();
		String[] grid={"AAAAA",
					   "AAAAA",
					   "AAAAA",
					   "AAAAA",
					   "AAAAA"};
		String find="AAAAAAAAAAA";
		int key=-1;
		int result=wp.countPaths(grid,find);
		System.out.println (result);
		return result==key;
	}
	public static boolean Test7() {
		WordPath wp=new WordPath();
		String[] grid={"AB",
                       "CD"};
		String find="AA";
		int key=0;
		int result=wp.countPaths(grid,find);
		System.out.println (result);
		return result==key;
	}
	public static void main(String[] args) {
		if (Test1())
			System.out.println ("Test1 OK");
		if (Test2())
			System.out.println ("Test2 OK");
		if (Test3())
			System.out.println ("Test3 OK");
		if (Test4())
			System.out.println ("Test4 OK");
		if (Test5())
			System.out.println ("Test5 OK");
		if (Test6())
			System.out.println ("Test6 OK");
		if (Test7())
			System.out.println ("Test7 OK");
	}
}