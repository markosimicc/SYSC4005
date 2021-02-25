public class workStation {
    private int id;
    private boolean isBusy;
    private ArrayList<Queue<factoryComponent>> workstationQueues;
    public workStation(int ID){
        id = ID;
        isBusy = false;
        workstationQueues = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
