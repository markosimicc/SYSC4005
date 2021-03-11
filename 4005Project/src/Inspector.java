import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Inspector {
    private int id;
    private boolean blocked;
    private ArrayList<factoryComponent> designatedComponents;
    private factoryComponent currentComponent;
    private Queue<Queue<factoryComponent>> workstationQueues;
    private int currentWorkstation;

    public Inspector(int ID,ArrayList<factoryComponent> components,Queue<Queue<factoryComponent>> workstationQueues){
        this.id = ID;
        blocked = false;
        designatedComponents = components;
        this.workstationQueues = workstationQueues;
        currentWorkstation = 0;
    }
    
    public boolean checkFull(){
    	System.out.println(workstationQueues.size());
    	int numofQueues = workstationQueues.size();
    	int fullQueues = 0;
		for(int i = 0; i < numofQueues;i++) {
			Queue<factoryComponent> queue = workstationQueues.poll();
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
    
    //public factoryComponent randomComponent() {
    	//int i = rand(designatedComponents.size() - 1);
    	//return designtedComponents(i);
   // }

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
    public Queue<factoryComponent> addToQueue(factoryComponent comp){
        for(int i = 0; i < workstationQueues.size(); i++) {
        	Queue<factoryComponent> workstationqueue = workstationQueues.poll();
        	System.out.println(workstationqueue.size());
        	if(workstationqueue.size() < 2) {
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
