import java.util.*;
public class BusStops {
	class Location {
		int x,y;
	}
	int countStops(String[] cityMap, int walkingDistance) {
		int count=0;
		Location current=new Location();
		Vector v=new Vector();
		for (int i=0;i<cityMap.length;i++) {
			for (int j=0;j<cityMap[i].length();j++) {
				char c=cityMap[i].charAt(j);
				if (c!='.') {
					if (c=='X') {
						current.x=i;
						current.y=j;
					}
					else {
						Location l=new Location();
						l.x=i;
						l.y=j;
						v.add(l);
					}
				}
			} 
		}
		Enumeration em=v.elements();
		while (em.hasMoreElements()) {
			Location l=(Location)em.nextElement();
			if (judgeReach(current,l,walkingDistance)) {
				count++;
			}
		}
		return count;
	}
	boolean judgeReach(Location l1,Location l2,int d) {
		int direction=Math.abs(l1.x-l2.x)+Math.abs(l1.y-l2.y);
		if (direction<=d) {
			return true;
		}
		else return false;
	}
}