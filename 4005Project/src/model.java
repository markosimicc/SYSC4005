import java.util.Queue;

public class model {
    //Initializing Simulation Variables
    public static int productsCreated, workHours, timeWorked;

    //Initializing Queues for workstations
    private Queue<factoryComponent> W1_1;
    private Queue<factoryComponent> W2_1;
    private Queue<factoryComponent> W2_2;
    private Queue<factoryComponent> W3_1;
    private Queue<factoryComponent> W3_2;


    public static void Initialize() {
        productsCreated = 0;
        workHours = 5;
        timeWorked = 0;
        //Read in all inspector data points as event
    }

    public static boolean checkTime(Event event){
        return timeWorked >= workHours;
    }

    public static void processEvent(Event event){
        /*
        Check Event Type()
        IF INPI:
            If Inspector is not Busy
            read in data point according to component type and create event of INPE for export and add to Event Array
            If Busy
                create new identical event with time finished of the next closest event in the queue
        If INPE:
            set timeworked to timefinished
            Have Inspector check it's available queues
                if queues are full, create new identical event with time finished of the next closest event in the queue
                else add a component of same type to the first in the queue of queues (which is then added to the back of queue of queues)
                Create a WSTI event for the corresponding workstation and add it to the event queue
        If WSTI:
        If Workstation is not busy
            remove first instance of components from respective queues
            read in data point according to the workstations data sheet
             and create event type of WSTE and add to event queue
        If Busy
            create new identical event with time finished of the next closest event in the queue
        if WSTE:
        set timeworked to timefinished
        increase number of products finished
         */

    }

    /////////////////////////////
    //Queue of Created Events
    //While(dayEnded)
    /*
    Find closest event time (if closest time is above workHours, set dayEnded to True)
    perform duties of event
     */

}