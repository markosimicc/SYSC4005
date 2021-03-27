import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class workStation {
	private int id;
	private boolean isBusy;
	private int fullQueue;
	private ArrayList<Queue<factoryComponent>> workstationQueues;
	private ArrayList<factoryComponent> designatedComponents;
	private double initialValue;

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
		System.out.println(numofQueues + " Queues for workstation " + id);
		int fullQueues = 0;
		for (int i = 0; i < numofQueues; i++) {
			System.out.println("Queue " + i + " has a size of " + workstationQueues.get(i).size());
			if (workstationQueues.get(i).size() >= 1) {
				System.out.println("Queue " + i + " has a component!");
				fullQueues = fullQueues + 1;
				System.out.println(fullQueues + "/" + numofQueues + "With components in them");
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

	public void removefromQueues(ArrayList<factoryComponent> designatedComponents) {
		int removedcomponents = 0;
		for (int i = 0; i < workstationQueues.size(); i++) {
			for (int j = 0; j < designatedComponents.size(); j++) {
				if (removedcomponents == designatedComponents.size()) {
					return;
				}
				if (workstationQueues.get(i).contains(designatedComponents.get(j))) {
					workstationQueues.get(i).remove(designatedComponents.get(j));
					removedcomponents++;
				}
			}
		}
	}

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
		case (1):
			//a = 23;
			//c = 47;
			m = 29.5;
			randomvalue = (a * initialValue + c) % m;
			this.initialValue = randomvalue;
			lambda = 0.217183;
			r = randomvalue/m;
			execTime = (-1/lambda) * Math.log(r);
			System.out.println("Workstation One with execution time " + execTime);
			break;
		case (2):
			//a = 14;
			//c = 30;
			m = 58;
			randomvalue = (a * initialValue + c) % m;
			this.initialValue = randomvalue;
			lambda = 0.0902;
			r = randomvalue/m;
			execTime = (-1/lambda) * Math.log(r);
			System.out.println("Workstation Two with execution time " + execTime);
			break;
		case (3):
			//a = 21;
			//c = 32;
			m = 51;
			randomvalue = (a * initialValue + c) % m;
			this.initialValue = randomvalue;
			lambda = 0.113693;
			r = randomvalue/m;
			execTime = (-1/lambda) * Math.log(r);
			System.out.println("Workstation Three with execution time " + execTime);
		}
		return execTime;
	}
}
