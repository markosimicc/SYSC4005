public class Event {
    int eventfTime;
    int eventsTime;
    ArrayList<factoryComponent> eventComponents;
    EventTypes eventType;

    public Event (int time,int clock,ArrayList<factoryComponent> components,EventTypes type){
        eventfTime = time + clock;
        eventsTime = clock;
        eventComponents = components;
        eventType = type;
    }

}
