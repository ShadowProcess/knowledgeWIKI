public class GoogleTest {
	public boolean compareString(String[] s1,String[] s2) {
		if (s1==null||s2==null||s1.length!=s2.length)
			return false;
		for (int i=0;i<s1.length;i++) {
			if (!s1[i].equals(s2[i]))
				return false;
		}
		return true;
	}
	public boolean Test1() {
		String[] result=
		{"XXXXXXXXXXXXXXXXXXXX",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "X..................X",
		 "XXXXXXXXXXXXXXXXXXXX"};
		 String[] input={"FORWARD 19", "LEFT", "FORWARD 19", "LEFT", "FORWARD 19", "LEFT", "FORWARD 19"};
		 DrawLines dl=new DrawLines();
		 String[] test=dl.execute(input);
		 return this.compareString(test,result);
	}
	public boolean Test2() {
		String[] result=
		{"....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "...................." };
		 String[] input={"LEFT", "LEFT", "LEFT", "LEFT", "LEFT", "LEFT", "LEFT", "LEFT"};
		 DrawLines dl=new DrawLines();
		 String[] test=dl.execute(input);
		 return this.compareString(test,result);
	}
	public boolean Test3() {
		String[] result=
		{"X...................",
		 "X...................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "....................",
		 "...................." };
		 String[] input={"FORWARD 1"};
		 DrawLines dl=new DrawLines();
		 String[] test=dl.execute(input);
		 return this.compareString(test,result);
	}
	public boolean Test4() {
		String[] result=
		{"XXXXXXXXXXXXXXXXXXXX",
		 "...................X",
		 "..XXXXXXXXXXXXXXXX.X",
		 "..X..............X.X",
		 "..X.XXXXXXXXXXXX.X.X",
		 "..X.X..........X.X.X",
		 "..X.X.XXXXXXXX.X.X.X",
		 "..X.X.X........X.X.X",
		 "..X.X.X........X.X.X",
		 "..X.X.X........X.X.X",
		 "..X.X.X........X.X.X",
		 "..X.X.X........X.X.X",
		 "..X.X.X........X.X.X",
		 "..X.X.X........X.X.X",
		 "..X.X.XXXXXXXXXX.X.X",
		 "..X.X............X.X",
		 "..X.XXXXXXXXXXXXXX.X",
		 "..X................X",
		 "..XXXXXXXXXXXXXXXXXX",
		 "...................." };
		 String[] input={"LEFT", "FORWARD 19", "LEFT", "LEFT", "LEFT",
						 "FORWARD 18", "LEFT", "LEFT", "LEFT", "FORWARD 17",
						 "LEFT", "LEFT", "LEFT", "FORWARD 16", "LEFT",
						 "LEFT", "LEFT", "FORWARD 15", "LEFT", "LEFT", "LEFT",
						 "FORWARD 14", "LEFT", "LEFT", "LEFT", "FORWARD 13",
						 "LEFT", "LEFT", "LEFT", "FORWARD 12", "LEFT", "LEFT",
						 "LEFT", "FORWARD 11", "LEFT", "LEFT", "LEFT", "FORWARD 10",
						 "LEFT", "LEFT", "LEFT", "FORWARD 9", "LEFT", "LEFT",
						 "LEFT", "FORWARD 8", "LEFT", "LEFT", "LEFT", "FORWARD 7"};
		 DrawLines dl=new DrawLines();
		 String[] test=dl.execute(input);
		 return this.compareString(test,result);
	}
}