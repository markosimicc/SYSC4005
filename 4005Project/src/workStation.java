import java.util.ArrayList;
import java.util.Queue;

public class workStation {
    private int id;
    private boolean isBusy;
    private int fullQueue;
    private ArrayList<Queue<factoryComponent>> workstationQueues;
    private ArrayList<factoryComponent> designatedComponents;
    public workStation(int ID,ArrayList<Queue<factoryComponent>> workstationQueues,ArrayList<factoryComponent> designatedComponents){
        id = ID;
        isBusy = false;
        this.workstationQueues = workstationQueues;
        this.designatedComponents = designatedComponents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ArrayList<Queue<factoryComponent>> getworkstationQueues(){
    	return workstationQueues;
    }
    
    public boolean checkForComponents(){
    	int numofQueues = workstationQueues.size();
    	int fullQueues = 0;
		for(int i = 0; i < numofQueues;i++) {
			if (workstationQueues.get(i).size() == 2) {
				fullQueues = fullQueues + 1;
			}
		}
		if(fullQueues == numofQueues) {
			return true;
		}
		else {
		return false;
		}
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
    	System.out.println("Removing " + removedcomponents + " components");
    	for(int i = 0; i < workstationQueues.size();i++) {
    		for(int j = 0; j < designatedComponents.size();j++) {
    			System.out.println(removedcomponents + "components removed");
    			if(removedcomponents == designatedComponents.size()) {
    				return;
    			}
    			if(workstationQueues.get(i).contains(designatedComponents.get(j))){
    				workstationQueues.get(i).remove(designatedComponents.get(j));
    				removedcomponents++;
    				System.out.println("Component Removed");
    		}
    		}
		}
    }
}
