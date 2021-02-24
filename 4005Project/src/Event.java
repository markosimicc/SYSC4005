public class Event {
    int eventTime;
    factoryComponent eventComponent;
    EventTypes eventType;

    public Event (int time,int clock,factoryComponent component,EventTypes type){
        eventTime = time + clock;
        eventComponent = component;
        eventType = type;
    }

}
