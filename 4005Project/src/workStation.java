public class workStation {
    private int id;
    private boolean isBusy;
    public workStation(int ID){
        id = ID;
        isBusy = false;
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
