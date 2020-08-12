public class TestMain {
	public static void main(String[] args) {
		GoogleTest gt=new GoogleTest();
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