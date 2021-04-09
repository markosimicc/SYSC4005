import java.util.ArrayList;
/**
 * A type of Event, that is focused on the events that the inspector performs
 * @author Eric Vincent
 *
 */
public class InspectorEvent implements Event {
	private double eventfTime;
	private double eventsTime;
	private factoryComponent fc;
	private Inspector insp;
	private EventTypes eventType;
	
	public InspectorEvent () {
		
	}
	/**
	 * The Constructor for the Inspector Events
	 * @param time the execution time of the event
	 * @param clock the time that the event will start
	 * @param insp the inspector that will handle the event
	 * @param fc the component that is being used in the event
	 * @param type the type of event being performed
	 */
    public InspectorEvent (double time,double clock, Inspector insp, factoryComponent fc,EventTypes type){
        eventfTime = time + clock;
        eventsTime = clock;
        this.fc = fc;
        this.insp = insp;
        eventType = type;
    }
    /**
	 * Manipulate the event for a new Inspector event
	 * @param time the execution time of the event
	 * @param clock the time that the event will start
	 * @param insp the inspector that will handle the event
	 * @param fc the component that is being used in the event
	 * @param type the type of event being performed
	 */
    public void changeEvent(double time,double clock, Inspector insp, factoryComponent fc,EventTypes type){
        eventfTime = time + clock;
        eventsTime = clock;
        this.fc = fc;
        this.insp = insp;
        eventType = type;
    }

	public double getEventfTime() {
		return eventfTime;
	}


	public double getEventsTime() {
		return eventsTime;
	}


	public factoryComponent getFc() {
		return fc;
	}


	public Inspector getInsp() {
		return insp;
	}


	public EventTypes getEventType() {
		return eventType;
	}
	
	public String toString() {
		String result = "" + (eventfTime - eventsTime) + "," + eventsTime + "," + insp.getId() + "," + fc.getComponentType() + "," + eventType;
		return result;
	}



}
