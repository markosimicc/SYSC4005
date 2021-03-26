import java.util.LinkedList;
import java.util.Queue;

public class buffer extends LinkedList<factoryComponent> {
	int id;
	int bufferNum;
	
	buffer(int id,int bufferNum){
		super();
		this.id = id;
		this.bufferNum = bufferNum;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	public int getID() {
		return id;
	}
	
	public int getbuffNum() {
		return bufferNum;
	}


public static void main(String[] args) {
	buffer w1_1 = new buffer(1,1);
	factoryComponent cmp1 = new factoryComponent(1);
	factoryComponent cmp2 = new factoryComponent(2);
	factoryComponent cmp3 = new factoryComponent(3);
	w1_1.add(cmp1);
	w1_1.setID(1);
	System.out.println(w1_1.getID());
	System.out.println(w1_1.toString());
}
}