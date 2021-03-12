import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Inspector {
    private int id;
    private boolean blocked;
    private ArrayList<factoryComponent> designatedComponents;
    private factoryComponent currentComponent;
    private Queue<buffer> workstationQueues;
    private int currentWorkstation;

    public Inspector(int ID,ArrayList<factoryComponent> components,Queue<buffer> workstationQueues){
        this.id = ID;
        blocked = false;
        designatedComponents = components;
        this.workstationQueues = workstationQueues;
        currentWorkstation = 0;
    }
    
    public boolean checkFull(){
    	int numofQueues = workstationQueues.size();
    	int fullQueues = 0;
		for(int i = 0; i < numofQueues;i++) {
			buffer queue = workstationQueues.poll();
			if (queue.size() == 2) {
				fullQueues = fullQueues + 1;
			}
			workstationQueues.add(queue);
		}
		if(fullQueues == numofQueues) {
			return true;
		}
		else {
		return false;
		}
    }
    

    public factoryComponent getRandom() {
    Random random = new Random();
    if(designatedComponents.size() >= 2) {
      if (random.nextBoolean()) {
        return designatedComponents.get(0);
      } else {
        return designatedComponents.get(1);
      }
    }
    return designatedComponents.get(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentComponent(factoryComponent component) {
        currentComponent = component;
        blocked = true;
    }
    public buffer addToQueue(factoryComponent comp){
    	int limit = workstationQueues.size();
        for(int i = 0; i < limit; i++) {
        	buffer workstationqueue = workstationQueues.poll();
        	if(workstationqueue.size() < 2 && comp.getComponentType() == workstationqueue.getID()) {
        		workstationqueue.add(comp);
        		workstationQueues.add(workstationqueue);
        		return workstationqueue;
        	}
        }
        return null;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public boolean getBlocked() {
        return blocked;
    }

}
