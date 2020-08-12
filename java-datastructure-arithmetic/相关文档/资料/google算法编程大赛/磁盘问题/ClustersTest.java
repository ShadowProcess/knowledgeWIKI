public class ClustersTest {
	public static boolean Test1() {
		DiskClusters dc=new DiskClusters();
		String disk=".XXXXXXXX.XXXXXX.XX.X.X.";
		int size=6;
		int result=6;
		return result==dc.minimumFragmentation(disk,size);
	}
	public static boolean Test2() {
		DiskClusters dc=new DiskClusters();
		String disk="XX..XX....X.XX........X...X.XX...XXXX..XX...XXXXX.";
		int size=12;
		int result=2;
		return result==dc.minimumFragmentation(disk,size);
	}
	public static boolean Test3() {
		DiskClusters dc=new DiskClusters();
		String disk=".X.XXXX.......XX....X.....X............XX.X.....X.";
		int size=20;
		int result=3;
		return result==dc.minimumFragmentation(disk,size);
	}
	public static boolean Test4() {
		DiskClusters dc=new DiskClusters();
		String disk="....X...X..X";
		int size=11;
		int result=-1;
		return result==dc.minimumFragmentation(disk,size);
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
	}
}