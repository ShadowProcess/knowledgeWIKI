public class DiskClusters {
	int[] clusters=new int[50];
	public int minimumFragmentation(String disk, int size) {
		char[] array=disk.toCharArray();
		int space=0,j=0,count=0;
		for (int i=0;i<array.length;i++) {
			if (array[i]=='.') {
				space++;
				clusters[j]+=1;
			}
			else if (i!=array.length-1) {
				if (array[i+1]=='.')
					j++;
			}
		}
		if (space<size)
			return -1;
		else {
			sort(j+1);
			int sum=0;
			for (int i=0;i<=j;i++) {
				count++;
				sum+=clusters[i];
				if (sum>=size)
					break;
			} 	
		}
		return count;
	}
	void sort(int length) {
		int i,j,k,temp;
		for (i=0;i<length;i++) {
			k=i;
			for (j=i;j<length;j++) {
				if (clusters[k]<clusters[j])
					k=j;
			}
			if (k!=i) {
				temp=clusters[k];
				clusters[k]=clusters[i];
				clusters[i]=temp;
			}
		}
	}
}