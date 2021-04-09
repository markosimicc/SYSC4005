/**
 * The components that the inspectors inspect, and that the workstations retreive to create their parts
 * @author Eric Vincent
 *
 */
public class factoryComponent {
    private int componentType;
    public factoryComponent(int type){
        componentType = type;
    }

    public void setComponentType(int componentType) {
        this.componentType = componentType;
    }

    public int getComponentType() {
        return componentType;
    }
    
    public String toString() {
		return "" + componentType;
    	
    }
}
