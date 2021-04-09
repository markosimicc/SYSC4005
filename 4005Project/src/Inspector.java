import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
/**
 * Inspector is in charge of handling the Inspector events
 * @author Eric Vincent
 *
 */
public class Inspector {
    private int id;
    private boolean blocked;
    private ArrayList<factoryComponent> designatedComponents;
    private factoryComponent currentComponent;
    private Queue<buffer> workstationQueues;
    private int currentWorkstation;
    private double initialValue;
    private double initialValue2;
    
    /**
     * The standard Constructor for Inspector
     * @param ID the inspector ID
     * @param components the components the inspector inspects
     * @param workstationQueues the queues it adds these components to
     * @param initialValue the seed of the randomizer
     */
    public Inspector(int ID,ArrayList<factoryComponent> components,Queue<buffer> workstationQueues,double initialValue){
        this.id = ID;
        blocked = false;
        designatedComponents = components;
        this.workstationQueues = workstationQueues;
        currentWorkstation = 0;
        this.initialValue = initialValue;
        initialValue2 = 0;
    }
   /**
    * Checks to see if the inspectors assigned queues are full
    * @return true if all the queues are full
    */
    public boolean checkFull(){
    	int numofQueues = workstationQueues.size();
    	int fullQueues = 0;
		for(int i = 0; i < numofQueues;i++) {
			buffer queue = workstationQueues.poll();
			//Max amount of components in a queue is 2, so if its size is 2, that means it's full
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
    
    /**
     * Get a random factory component from an inspectors collection of available components
     * @return the randomly chosen component
     */
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
    	//System.out.println("Number of Queues: " + limit);
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
    /**
     * Create a random time based on the inspector and the component it is inspecting
     * @return the randomized time it takes to inspect
     */
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
    	//Inspector 1
    		case (1):
    			m = 78;
    			randomvalue = (a*initialValue + c) % m;
    			this.initialValue = randomvalue;
    			lambda = 0.096545;
    			r = randomvalue/m;
    			execTime = (-1/lambda) * Math.log(r);
    	
    		case(2):
    			//Inspector 2 working on component 2
    			if(fc == 2) {
        			m = 115;
        			randomvalue = (a*initialValue + c) % m;
        			this.initialValue = randomvalue;
        			lambda = 0.064363;
        			r = randomvalue/m;
        			execTime = (-1/lambda) * Math.log(r);

        			
    			}
    			//Inspector 2 working on component 3
    			else if(fc == 3) {
        			m = 105;
        			randomvalue = (a*initialValue2 + c) % m;
        			this.initialValue2 = randomvalue;
        			lambda = 0.048467;
        			r = randomvalue/m;
        			execTime = (-1/lambda) * Math.log(r);			
    			}
    		break;
    	}
    	return execTime;	
    }

}
