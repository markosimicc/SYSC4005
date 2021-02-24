import java.util.ArrayList;

public class Inspector {
    private int id;
    private boolean blocked;
    private ArrayList<factoryComponent> designatedComponents;
    private factoryComponent currentComponent;

    public Inspector(int ID,ArrayList<factoryComponent> components){
        this.id = ID;
        blocked = false;
        designatedComponents = components;
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

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public boolean getBlocked() {
        return blocked;
    }
}
