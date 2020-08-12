public class WordPath {
	int m,n,count=0;
	char[] word;
	char[][] matrix;
	public int countPaths(String[] grid, String find) {
		m=grid.length;
		n=grid[0].length();
		matrix=new char[m][n];
		word=find.toCharArray();
		int i,j;
		for (i=0;i<m;i++) {
			for (j=0;j<n;j++) {
				matrix[i][j]=grid[i].charAt(j);
			}
		}
		for (i=0;i<m;i++) {
			for (j=0;j<n;j++) {
				if (matrix[i][j]==word[0]) {
					findNextChar(i,j,1);
				}
			}
		}
		if (count>1000000000)
			count=-1;
		return count;
	}
	boolean findNextChar(int x,int y,int c) {
		boolean found=false,findnext=false;
		if (x-1>=0&&x-1<m&&y-1>=0&&y-1<n) {
			if (matrix[x-1][y-1]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x-1,y-1,c+1))
						findnext=true;
				}
			}
		}
		if (x-1>=0&&x-1<m&&y>=0&&y<n) {
			if (matrix[x-1][y]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x-1,y,c+1))
						findnext=true;
				}
			}
		}
		if (x-1>=0&&x-1<m&&y+1>=0&&y+1<n) {
			if (matrix[x-1][y+1]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x-1,y+1,c+1))
						findnext=true;
				}
			}
		}
		if (x>=0&&x<m&&y-1>=0&&y-1<n) {
			if (matrix[x][y-1]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x,y-1,c+1))
						findnext=true;
				}
			}
		}
		if (x>=0&&x<m&&y+1>=0&&y+1<n) {
			if (matrix[x][y+1]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x,y+1,c+1))
						findnext=true;
				}
			}
		}
		if (x+1>=0&&x+1<m&&y-1>=0&&y-1<n) {
			if (matrix[x+1][y-1]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x+1,y-1,c+1))
						findnext=true;
				}
			}	
		}
		if (x+1>=0&&x+1<m&&y>=0&&y<n) {
			if (matrix[x+1][y]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x+1,y,c+1))
						findnext=true;
				}
			}
		}
		if (x+1>=0&&x+1<m&&y+1>=0&&y+1<n) {
			if (matrix[x+1][y+1]==word[c]) {
				found=true;
				if (count>1000000000) {
					return false;
				}
				if (c==word.length-1) {
					count++;
					findnext=true;
				}
				else {
					if (findNextChar(x+1,y+1,c+1))
						findnext=true;
				}
			}
		}
		if (found&&findnext)
			return true;
		else return false;
	}
}