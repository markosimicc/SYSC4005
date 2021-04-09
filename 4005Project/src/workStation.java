import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
/**
 * A part of the model, responsible for handling workstation events
 * @author Eric Vincent
 *
 */
public class workStation {
	private int id;
	private boolean isBusy;
	private int fullQueue;
	private ArrayList<Queue<factoryComponent>> workstationQueues;
	private ArrayList<factoryComponent> designatedComponents;
	private double initialValue;
	/**
	 * Constructor for the Workstation
	 * @param ID The ID of the Workstation
	 * @param workstationQueues the Queues that the workstation retrieves their components from
	 * @param designatedComponents The components that the workstation uses
	 * @param initialValue The seed to produce it's randomized times
	 */
	public workStation(int ID, ArrayList<Queue<factoryComponent>> workstationQueues,
			ArrayList<factoryComponent> designatedComponents, double initialValue) {
		id = ID;
		isBusy = false;
		this.workstationQueues = workstationQueues;
		this.designatedComponents = designatedComponents;
		this.initialValue = initialValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Queue<factoryComponent>> getworkstationQueues() {
		return workstationQueues;
	}
	
	public boolean checkForComponents() {
		int numofQueues = workstationQueues.size();
		//System.out.println(numofQueues + " Queues for workstation " + id);
		int fullQueues = 0;
		for (int i = 0; i < numofQueues; i++) {
			//System.out.println("Queue " + i + " has a size of " + workstationQueues.get(i).size());
			if (workstationQueues.get(i).size() >= 1) {
				//System.out.println("Queue " + i + " has a component!");
				fullQueues = fullQueues + 1;
				//System.out.println(fullQueues + "/" + numofQueues + "With components in them");
				if (fullQueues == numofQueues) {
					return true;
				}
			}
		}
			return false;
	}

	public void setBusy(boolean busy) {
		isBusy = busy;
	}

	public boolean getisBusy() {
		return isBusy;
	}

	public ArrayList<factoryComponent> getDesignatedComponents() {
		return designatedComponents;
	}
	/**
	 * Removes the components being used to create the part
	 * @param designatedComponents the queues of the workstations components that it will take from
	 */
	public void removefromQueues(ArrayList<factoryComponent> designatedComponents) {
		int removedcomponents = 0;
		for (int i = 0; i < workstationQueues.size(); i++) {
			for (int j = 0; j < designatedComponents.size(); j++) {
				//If one component was removed from each queue, then it has finishished executing
				if (removedcomponents == designatedComponents.size()) {
					return;
				}
				//If the queues has at least one of the needed component
				if (workstationQueues.get(i).contains(designatedComponents.get(j))) {
					//remove that component
					workstationQueues.get(i).remove(designatedComponents.get(j));
					removedcomponents++;
				}
			}
		}
	}
	/**
	 * Creates a randomized time for the workstation to produce its part, depending on what workstation it is
	 * @return the randomized time
	 */
	public double generateWorkstationTime() {
		Random rand = new Random();
    	double a = rand.nextDouble();
    	double c = rand.nextDouble();
		double m;
		double randomvalue;
		double lambda;
		double execTime = 0;
		double r;
		switch (id) {
		//Workstation 1
		case (1):
			m = 29.5;
			randomvalue = (a * initialValue + c) % m;
			this.initialValue = randomvalue;
			lambda = 0.217183;
			r = randomvalue/m;
			execTime = (-1/lambda) * Math.log(r);
			break;
		//Workstation 2
		case (2):
			m = 58;
			randomvalue = (a * initialValue + c) % m;
			this.initialValue = randomvalue;
			lambda = 0.0902;
			r = randomvalue/m;
			execTime = (-1/lambda) * Math.log(r);
			break;
		//Workstation 3
		case (3):
			m = 51;
			randomvalue = (a * initialValue + c) % m;
			this.initialValue = randomvalue;
			lambda = 0.113693;
			r = randomvalue/m;
			execTime = (-1/lambda) * Math.log(r);
		}
		return execTime;
	}
}
