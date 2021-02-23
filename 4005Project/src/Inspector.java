public class Inspector {
    private int id;
    private boolean blocked;
    private factoryComponent currentComponent;

    public Inspector(int ID){
        this.id = ID;
        blocked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentComponent(factoryComponent currentComponent) {
        this.currentComponent = currentComponent;
        blocked = true;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public boolean getBlocked() {
        return blocked;
    }
}
