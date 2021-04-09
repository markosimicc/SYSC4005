import java.util.ArrayList;
/**
 * A type of Event, that is focused on the events that the workstation performs
 * @author Eric Vincent
 *
 */
public class WorkStationEvent implements Event {
	private double eventfTime;
	private double eventsTime;
	private workStation wrkst;
	private EventTypes eventType;

	public WorkStationEvent () {
		
	}
	
	/**
	 * Standard Constructor for Workstation events
	 * @param time the execution time of the event
	 * @param clock the time that the event will start
	 * @param wrkst the workstation that will do the behaviors
	 * @param type the type of event
	 */
    public WorkStationEvent (double time,double clock, workStation wrkst, EventTypes type){
        eventfTime = time + clock;
        eventsTime = clock;
        this.wrkst = wrkst;
        eventType = type;
    }
    /**
	 * Manipulate the event for a new workstation event
	 * @param time the execution time of the event
	 * @param clock the time that the event will start
	 * @param wrkst the workstation that will do the behaviors
	 * @param type the type of event
	 */
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
