public class BusStopTest {
	public boolean Test1() {
		BusStops bs=new BusStops();
		String[] map={"...B.",
					  ".....",
					  "..X.B",
					  ".....",
					  "B...."};
		int d=3;
		int result=2;
		return result==bs.countStops(map,d);
	}
	public boolean Test2() {
		BusStops bs=new BusStops();
		String[] map={"B.B..",
					 ".....",
					 "B....",
					 ".....",
					 "....X"};
		int d=8;
		int result=3;
		return result==bs.countStops(map,d);
	}
	public boolean Test3() {
		BusStops bs=new BusStops();
		String[] map={"BBBBB",
					  "BB.BB",
					  "B.X.B",
					  "BB.BB",
					  "BBBBB"};
		int d=1;
		int result=0;
		return result==bs.countStops(map,d);
	}
	public boolean Test4() {
		BusStops bs=new BusStops();
		String[] map={"B..B..",
					  ".B...B",
					  "..B...",
					  "..B.X.",
					  "B.B.B.",
					  ".B.B.B"};
		int d=3;
		int result=7;
		return result==bs.countStops(map,d);
	}
	public static void main(String[] args) {
		
		String[] map={"BBBBB",
					  "BB.BB",
					  "B.X.B",
					  "BB.BB",
					  "BBBBB"};
					  
		System.out.println (map);
		
		BusStopTest bs=new BusStopTest();
		if (bs.Test1()) {
			System.out.println ("Test1 OK");
		}
		if (bs.Test2()) {
			System.out.println ("Test2 OK");
		}
		if (bs.Test3()) {
			System.out.println ("Test3 OK");
		}
		if (bs.Test4()) {
			System.out.println ("Test4 OK");
		}
	}
}