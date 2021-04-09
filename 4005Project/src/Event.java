/**
 * Interface that Workstation and Inspector Events extends
 * @author Eric Vincent
 *
 */
public interface Event {
    
	 public EventTypes getEventType();
	 public double getEventfTime();
	 public double getEventsTime();
	 public String toString();
}
