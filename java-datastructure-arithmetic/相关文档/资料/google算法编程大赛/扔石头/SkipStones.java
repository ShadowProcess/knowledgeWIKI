public class SkipStones {
	int maxDistance(String water) {
		int n=water.length()/2+1;
		boolean ok=true;
		int temp,result=0;
		char[] str=water.toCharArray();
		for(int i=n;i>0;i--) {
			ok=true;
			temp=i;
			result=i;
			while (temp>=1) {
				if (str[result-1]=='X') {
					ok=false;
					break;
				}
				temp=temp/2;
				result+=temp;
			}
			if (ok) {
				break;
			}
		}
		return result;
    }
}