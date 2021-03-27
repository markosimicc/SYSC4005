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
    private double initialValue;
    private double initialValue2;

    public Inspector(int ID,ArrayList<factoryComponent> components,Queue<buffer> workstationQueues,double initialValue){
        this.id = ID;
        blocked = false;
        designatedComponents = components;
        this.workstationQueues = workstationQueues;
        currentWorkstation = 0;
        this.initialValue = initialValue;
        initialValue2 = 0;
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
			if(fullQueues == numofQueues) {
				return true;
			}
		}
		return false;
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
    
    public void setInitialValue2(double value) {
    	initialValue2 = value;
    }
    public void setCurrentComponent(factoryComponent component) {
        currentComponent = component;
        blocked = true;
    }
    /**
     * Method to add a component to the assigned Queues of the inspector
     * @param comp the component to be added to one of the Queues
     * @return the Queue that the component was added to if there is space, null if all queues are full.
     */
    public buffer addToQueue(factoryComponent comp){
    	//Get the amount of queues available to the inspector
    	int limit = workstationQueues.size();
    	System.out.println("Number of Queues: " + limit);
    	//for each queue
        for(int i = 0; i < limit; i++) {
        	//get the queue of components
        	buffer workstationqueue = workstationQueues.poll();
        	//if the queue of components aren't full (greater than 2) and the queue accepts the component (they have the same id value)
        	if(workstationqueue.size() < 2 && comp.getComponentType() == workstationqueue.getID()) {
        		//Component is added to the workstation queue
        		workstationqueue.add(comp);
        		//queue is added to the back of the line 
        		workstationQueues.add(workstationqueue);
        		return workstationqueue;
        	}
        	//queue is added to the back of the line 
        	workstationQueues.add(workstationqueue);
        }
        return null;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public boolean getBlocked() {
        return blocked;
    }
    
    public double generateInspectorTime() {
    	Random rand = new Random();
    	double a = rand.nextDouble();
    	double c = rand.nextDouble();
    	double m;
    	double randomvalue;
    	double lambda;
    	double execTime = 0;
    	double r;
    	int fc = currentComponent.getComponentType();
    	switch(id) {
    		case (1):
    			//a = 23;
    			//c = 22;
    			m = 78;
    			randomvalue = (a*initialValue + c) % m;
    			this.initialValue = randomvalue;
    			lambda = 0.096545;
    			r = randomvalue/m;
    			execTime = (-1/lambda) * Math.log(r);
    			System.out.println("Random Value " + randomvalue);
    			System.out.println("Inspector One with execution time " + execTime);
    		case(2):
    			if(fc == 2) {
    				//a = 45;
        			//c = 14;
        			m = 115;
        			randomvalue = (a*initialValue + c) % m;
        			this.initialValue = randomvalue;
        			lambda = 0.064363;
        			r = randomvalue/m;
        			execTime = (-1/lambda) * Math.log(r);
        			System.out.println("Random Value " + randomvalue);
        			System.out.println("Inspector Two (Comp 2) with execution time " + execTime);
        			
    			}
    			else if(fc == 3) {
    				//a = 5;
        			//c = 14;
        			m = 105;
        			randomvalue = (a*initialValue2 + c) % m;
        			this.initialValue2 = randomvalue;
        			lambda = 0.048467;
        			r = randomvalue/m;
        			execTime = (-1/lambda) * Math.log(r);
        			System.out.println("Random Value " + randomvalue);
        			System.out.println("Inspector Two (Comp 3) with execution time " + execTime);  			
    			}
    		break;
    	}
    	return execTime;	
    }

}
