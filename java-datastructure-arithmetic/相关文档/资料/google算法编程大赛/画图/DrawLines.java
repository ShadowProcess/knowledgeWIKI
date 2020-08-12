public class DrawLines {
	String[] execute(String[] commands) {
		char[][] map=new char[20][20];
		int x=0,y=0,direction=0,i,j;
		String[] result=new String[20];
		for (i=0;i<20;i++) {
			result[i]="";
			for (j=0;j<20;j++)
				map[i][j]='.';
		}
		for (int k=0;k<commands.length;k++) {
			if (commands[k].equals("LEFT")) {
				direction=(direction+1)%4;
			}else if(commands[k].split(" ")[0].equals("FORWARD")) {
				map[0][0]='X';
				int step=Integer.parseInt(commands[k].split(" ")[1]);
				switch (direction) {
					case 0:
					for (i=0;i<step;i++) {
						if (x<20)
							map[++x][y]='X';
						else break;
					}
					break;
					case 1:
					for (i=0;i<step;i++) {
						if (y<20)
							map[x][++y]='X';
						else break;
					}
					break;
					case 2:
					for (i=0;i<step;i++) {
						if (x>=0)
							map[--x][y]='X';
						else break;
					}
					break;
					case 3:
					for (i=0;i<step;i++) {
						if (y>=0)
							map[x][--y]='X';
						else break;
					}
					break;
				}
			}
		}
		for (i=0;i<20;i++)
			for (j=0;j<20;j++)
				result[i]+=map[i][j];
		return result;
	}
	public static void main(String[] args) {
		String[] commands={"LEFT", "FORWARD 19", "LEFT", "LEFT", "LEFT",
 "FORWARD 18", "LEFT", "LEFT", "LEFT", "FORWARD 17",
 "LEFT", "LEFT", "LEFT", "FORWARD 16", "LEFT",
 "LEFT", "LEFT", "FORWARD 15", "LEFT", "LEFT", "LEFT",
 "FORWARD 14", "LEFT", "LEFT", "LEFT", "FORWARD 13",
 "LEFT", "LEFT", "LEFT", "FORWARD 12", "LEFT", "LEFT",
 "LEFT", "FORWARD 11", "LEFT", "LEFT", "LEFT", "FORWARD 10",
 "LEFT", "LEFT", "LEFT", "FORWARD 9", "LEFT", "LEFT",
 "LEFT", "FORWARD 8", "LEFT", "LEFT", "LEFT", "FORWARD 7"};
		DrawLines dl=new DrawLines();
		String[] result=dl.execute(commands);
		for (int i=0;i<20;i++)
			System.out.println (result[i]);
	}
}