import java.util.ArrayList;

public class WorkStationEvent implements Event {
	private double eventfTime;
	private double eventsTime;
	private workStation wrkst;
	private EventTypes eventType;

	public WorkStationEvent () {
		
	}
	
    public WorkStationEvent (double time,double clock, workStation wrkst, EventTypes type){
        eventfTime = time + clock;
        eventsTime = clock;
        this.wrkst = wrkst;
        eventType = type;
    }
    
    public void changeEvent(double time,double clock, workStation newwrkst, EventTypes type){
        eventfTime = time + clock;
        eventsTime = clock;
        this.wrkst = newwrkst;
        eventType = type;
    }

	public double getEventfTime() {
		return eventfTime;
	}


	public double getEventsTime() {
		return eventsTime;
	}



	public EventTypes getEventType() {
		return eventType;
	}



	public workStation getWrkst() {
		return wrkst;
	}
	
	public String toString() {
		String result = "" + (eventfTime - eventsTime) + "," + eventsTime + "," + wrkst.getId() + "," + eventType;
		return result;
	}
}
