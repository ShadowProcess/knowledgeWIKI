import java.util.*;
public class PlayCards {
	Vector s=new Vector();
	Vector h=new Vector();
	Vector d=new Vector();
	Vector c=new Vector();
	String[] value=new String[10];
	void setSuits(String[] cards) {
		for (int i=0;i<cards.length;i++) {
			if (cards[i].split(" ")[1].equals("S")) {
				s.add(cards[i].split(" ")[0]);
			}
			else if (cards[i].split(" ")[1].equals("H")) {
				h.add(cards[i].split(" ")[0]);
			}
			else if (cards[i].split(" ")[1].equals("D")) {
				d.add(cards[i].split(" ")[0]);
			}
			else if (cards[i].split(" ")[1].equals("C")) {
				c.add(cards[i].split(" ")[0]);
			}
		}
	}
	void setValues(String[] cards) {
		for (int i=0;i<cards.length;i++) {
			int point=Integer.parseInt(cards[i].split(" ")[0]);
			value[point-1]+=cards[i].split(" ")[1];
		}
	}
	public int maxCards(String[] cards) {
		if (cards.length<3)
			return 0;
		return 0;
	}
}