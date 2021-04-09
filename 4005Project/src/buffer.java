import java.util.LinkedList;
import java.util.Queue;
/**
 * Allows the queue to have a set ID, which determines which component it's a queue for
 * @author Eric Vincent
 *
 */
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

}