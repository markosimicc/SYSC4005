import java.util.ArrayList;

public class InspectorEvent implements Event {
	private double eventfTime;
	private double eventsTime;
	private factoryComponent fc;
	private Inspector insp;
	private EventTypes eventType;
	
	public InspectorEvent () {
		
	}

    public InspectorEvent (double time,double clock, Inspector insp, factoryComponent fc,EventTypes type){
        eventfTime = time + clock;
        eventsTime = clock;
        this.fc = fc;
        this.insp = insp;
        eventType = type;
    }
    
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
