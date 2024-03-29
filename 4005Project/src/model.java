import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
/**
 * The Model that will run the simulation in order to find the amount of components made and block occurrence
 * @author Eric Vincent
 *
 */
public class model {

	public static int productsCreated, workTime, inspectorBlockCount;
	public static double timeWorked;
	public static boolean timeEnded;
	public static ArrayList<Event> FEL;
	public static Queue<InspectorEvent> inpi, inpe, inspb;
	public static Queue<WorkStationEvent> wsti, wste;
	public static workStation w1, w2, w3, w4;
	public static buffer w1_1,w2_1,w2_2,w3_1,w3_2,w4_1,w4_2;
	
	public static ArrayList<Double> i1times,i2_2times,i2_3times,w1times,w2times,w3times;
	

	public static void Initialize() {
		Random rand = new Random();
		// Initializing Queues for workstations
		w1_1 = new buffer(1,1);
		w2_1 = new buffer(1,2);
		w2_2 = new buffer(2,3);
		w3_1 = new buffer(1,4);
		w3_2 = new buffer(3,5);
		w4_1 = new buffer(1,6);
		w4_2 = new buffer(3,7);
		// Initializing Queues of the workstation Queues for the inspector class
		Queue<buffer> workstQueuesI1 = new LinkedList<buffer>();
		workstQueuesI1.add(w1_1);
		workstQueuesI1.add(w2_1);
		workstQueuesI1.add(w3_1);
		
		//Addition of queue for 4th workstation
		//workstQueuesI1.add(w4_1);
		
		Queue<buffer> workstQueuesI2 = new LinkedList<buffer>();
		workstQueuesI2.add(w2_2);
		workstQueuesI2.add(w3_2);
		
		//Addition of queue for 4th workstation
		//workstQueuesI2.add(w4_2);
		
		// Creating the components
		factoryComponent cmp1 = new factoryComponent(1);
		factoryComponent cmp2 = new factoryComponent(2);
		factoryComponent cmp3 = new factoryComponent(3);
		//Assigning Workstation with the their designated components
		ArrayList<factoryComponent> w1com = new ArrayList<factoryComponent>();
		w1com.add(cmp1);
		ArrayList<factoryComponent> w2com = new ArrayList<factoryComponent>();
		w2com.add(cmp1);
		w2com.add(cmp2);
		ArrayList<factoryComponent> w3com = new ArrayList<factoryComponent>();
		w3com.add(cmp1);
		w3com.add(cmp3);
		
		//Addition of fourth Workstation
		//ArrayList<factoryComponent> w4com = new ArrayList<factoryComponent>();
		//w4com.add(cmp1);
		//w4com.add(cmp3);
		
		// Creating the groups of usable components for each inspector
		ArrayList<factoryComponent> ins1com = new ArrayList<factoryComponent>();
		ins1com.add(cmp1);
		ArrayList<factoryComponent> ins2com = new ArrayList<factoryComponent>();
		ins2com.add(cmp2);
		ins2com.add(cmp3);
		Inspector ins1 = new Inspector(1, ins1com, workstQueuesI1,rand.nextDouble());
		Inspector ins2 = new Inspector(2, ins2com, workstQueuesI2,rand.nextDouble());
		ins2.setInitialValue2(rand.nextDouble());
		// Create WorkStations
		ArrayList<Queue<factoryComponent>> workstQueuesW1 = new ArrayList<Queue<factoryComponent>>();
		workstQueuesW1.add(w1_1);
		ArrayList<Queue<factoryComponent>> workstQueuesW2 = new ArrayList<Queue<factoryComponent>>();
		workstQueuesW2.add(w2_1);
		workstQueuesW2.add(w2_2);
		ArrayList<Queue<factoryComponent>> workstQueuesW3 = new ArrayList<Queue<factoryComponent>>();
		workstQueuesW3.add(w3_1);
		workstQueuesW3.add(w3_2);
		//Creating W4 queue
		//ArrayList<Queue<factoryComponent>> workstQueuesW4 = new ArrayList<Queue<factoryComponent>>();
		//workstQueuesW4.add(w4_1);
		//workstQueuesW4.add(w4_2);
		w1 = new workStation(1, workstQueuesW1,w1com,rand.nextDouble());
		w2 = new workStation(2, workstQueuesW2,w2com,rand.nextDouble());
		w3 = new workStation(3, workstQueuesW3,w3com,rand.nextDouble());
		//Creating 4th Workstation
		//w4 = new workStation(4, workstQueuesW4,w4com,rand.nextDouble());
		
		// Initializing Simulation Variables
		productsCreated = 0;
		workTime = 10000;
		timeWorked = 0;
		inspectorBlockCount = 0;
		timeEnded = false;
		
		// Creating Collection of Events to use
		InspectorEvent INPI1 = new InspectorEvent();
		InspectorEvent INPI2 = new InspectorEvent();
		InspectorEvent INPI3 = new InspectorEvent();
		InspectorEvent INPI4 = new InspectorEvent();
		inpi = new LinkedList<InspectorEvent>();
		inpi.add(INPI1);
		inpi.add(INPI2);
		inpi.add(INPI3);
		inpi.add(INPI4);

		InspectorEvent INPE1 = new InspectorEvent();
		InspectorEvent INPE2 = new InspectorEvent();
		InspectorEvent INPE3 = new InspectorEvent();
		InspectorEvent INPE4 = new InspectorEvent();
		inpe = new LinkedList<InspectorEvent>();
		inpe.add(INPE1);
		inpe.add(INPE2);
		inpe.add(INPE3);
		inpe.add(INPE4);

		InspectorEvent INSPB1 = new InspectorEvent();
		InspectorEvent INSPB2 = new InspectorEvent();
		InspectorEvent INSPB3 = new InspectorEvent();
		InspectorEvent INSPB4 = new InspectorEvent();
		inspb = new LinkedList<InspectorEvent>();
		inspb.add(INSPB1);
		inspb.add(INSPB2);
		inspb.add(INSPB3);
		inspb.add(INSPB4);

		WorkStationEvent WSTI1 = new WorkStationEvent();
		WorkStationEvent WSTI2 = new WorkStationEvent();
		WorkStationEvent WSTI3 = new WorkStationEvent();
		WorkStationEvent WSTI4 = new WorkStationEvent();
		WorkStationEvent WSTI5 = new WorkStationEvent();
		WorkStationEvent WSTI6 = new WorkStationEvent();
		wsti = new LinkedList<WorkStationEvent>();
		wsti.add(WSTI1);
		wsti.add(WSTI2);
		wsti.add(WSTI3);
		wsti.add(WSTI4);
		wsti.add(WSTI5);
		wsti.add(WSTI6);

		WorkStationEvent WSTE1 = new WorkStationEvent();
		WorkStationEvent WSTE2 = new WorkStationEvent();
		WorkStationEvent WSTE3 = new WorkStationEvent();
		WorkStationEvent WSTE4 = new WorkStationEvent();
		WorkStationEvent WSTE5 = new WorkStationEvent();
		WorkStationEvent WSTE6 = new WorkStationEvent();
		wste = new LinkedList<WorkStationEvent>();
		wste.add(WSTE1);
		wste.add(WSTE2);
		wste.add(WSTE3);
		wste.add(WSTE4);
		wste.add(WSTE5);
		wste.add(WSTE6);
		
		// Creating initial events
		InspectorEvent E1 = inpi.poll();
		E1.changeEvent(0, timeWorked, ins1, cmp1, EventTypes.INSPI);
		InspectorEvent E2 = inpi.poll();
		E2.changeEvent(0, timeWorked, ins2, ins2.getRandom(), EventTypes.INSPI);
		FEL = new ArrayList<Event>();
		FEL.add(E1);
		FEL.add(E2);
		
		//Initializing ArrayLists of all generated Times
		i1times = new ArrayList<Double>();
		i2_2times = new ArrayList<Double>();
		i2_3times = new ArrayList<Double>();
		w1times = new ArrayList<Double>();
		w2times = new ArrayList<Double>();
		w3times = new ArrayList<Double>();
	}
	/**
	 * Gets the closest event in FEL queue, based off their start time
	 * @return the event with the closest start time
	 */
	public static Event retreiveClosestEvent() {
		Event closestEvent = FEL.get(0);
		int index = 0;
		ArrayList<Event> closestevents = new ArrayList<Event>();
		closestevents.add(closestEvent);
		for (int i = 1; i < FEL.size(); i++) {
			if(FEL.get(i).getEventsTime() == closestEvent.getEventsTime()) {
				closestevents.add(FEL.get(i));
			}
			if (FEL.get(i).getEventsTime() < closestEvent.getEventsTime()) {
				closestEvent = FEL.get(i);
				closestevents.clear();
				closestevents.add(closestEvent);
			}
		}
		//Gets random event from the collection of events with the closest start time
		Random rand = new Random();
		Event chosenEvent = closestevents.get(rand.nextInt(closestevents.size()));
		index = FEL.indexOf(chosenEvent);
		FEL.remove(index);
		//System.out.println("Event Chosen: " + chosenEvent);
		return chosenEvent;
	}
	/**
	 * Get Closest event of a specified start time
	 * @param eventtype the event type to search for
	 * @return the event of the specified event type with the closest start time
	 */
	public static Event getClosestEvent(EventTypes eventtype) {
		Event closestEvent = FEL.get(0);
		for (int i = 1; i < FEL.size(); i++) {
			if (FEL.get(i).getEventsTime() < closestEvent.getEventsTime() && closestEvent.getEventType() == eventtype) {
				closestEvent = FEL.get(i);
			}
		}
		return closestEvent;
	}
	
	/**
	 * Checks if time worked has surpassed the time limit
	 * @return true if the time limit has been surpassed
	 */
	public static boolean checkTime() {
		return timeWorked >= workTime;
	}

	/**
	 * Returns what workstation has the specified queue
	 * @param workstationqueue the specified queue
	 * @param compNum the component that is used in the queue
	 * @return workstation number with the specified queue
	 */
	public static int findWorkstation(buffer workstationqueue, int compNum) {
		if(compNum == 2) {
			return 2;
		}
		if(compNum == 3) {
			return 3;
		}
		if (workstationqueue != null) {
			//System.out.println(workstationqueue.getbuffNum());
		if (workstationqueue.getbuffNum() == 1) {
			return 1;
		} else if (workstationqueue.getbuffNum() == 2 || workstationqueue.getbuffNum() == 3) {
			return 2;
		} else if (workstationqueue.getbuffNum() == 4 || workstationqueue.getbuffNum() == 5) {
			return 3;
		} else {
			return 0;
		}
		}
		else return 0;
	}
	/**
	 * Process the Event based off its event type
	 * @param event the event to be processed and it's behaviors executed
	 */
	public static void processEvent(Event event) {
		switch (event.getEventType()) {
		case INSPI:
			System.out.println("Importing to Inspector!");
			InspectorEvent e = (InspectorEvent) event;
			//If Inspector is blocked
			if (e.getInsp().getBlocked()) {
				System.out.println("Inspector is Busy!");
				//Create a new INSPI event with the same start time of the closest export event, as that export might free up the inspector
				Event closestEvent = getClosestEvent(EventTypes.INSPE);
				e.changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(), closestEvent.getEventsTime(),
						e.getInsp(), e.getFc(), EventTypes.INSPI);
				event = e;
				FEL.add(event);
			} else {
				//Inspector is not busy
				e.getInsp().setBlocked(true);
				e.getInsp().setCurrentComponent(e.getFc());
				Inspector insp = e.getInsp();
				//Generate the random time for the inspector
				double generatedTime = insp.generateInspectorTime();
				//Add to the stored collection of times
				if(insp.getId() == 1) {
					i1times.add(generatedTime);
				}
				else if(insp.getId() == 2) {
					if(e.getFc().getComponentType() == 2) {
						i2_2times.add(generatedTime);
					}
					else if(e.getFc().getComponentType() == 3) {
						i2_3times.add(generatedTime);
					}
				}
				//Create the new Export event to export the components into a workstation queue
				inpe.peek().changeEvent(generatedTime, timeWorked, e.getInsp(), e.getFc(), EventTypes.INSPE);
				FEL.add(inpe.poll());
				inpi.add((InspectorEvent) event);
			}
			break;

		case INSPE:
			System.out.println("Exporting From Inspector!");
			InspectorEvent e1 = (InspectorEvent) event;
			timeWorked = event.getEventfTime();
			//Check to see if there are queues available
			if (e1.getInsp().checkFull()) {
				//If full, create a block event with the closest WSTI, as that might free up one of the needed queues
				System.out.println("Inspectors Buffers are Full!");
				Event closestEvent = getClosestEvent(EventTypes.WSTI);
				//Create the WSTI event
				inspb.peek().changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(),
						closestEvent.getEventsTime(), e1.getInsp(), e1.getFc(), EventTypes.INSPB);
				FEL.add(inspb.poll());
				inpe.add((InspectorEvent) event);
				//Up the block count
				inspectorBlockCount++;
			}

			else {
				//If Queue is available, get the workstation of the queue we are adding to
				int station = findWorkstation(e1.getInsp().addToQueue(e1.getFc()),e1.getFc().getComponentType());
				System.out.println("Added to queue for workstation " + station);
				//For the workstation whos queue was just added to, check to see if they can create a component, if so, create an import event for that workstation
				switch (station) {
				case 1:
					if (w1.checkForComponents()) {
						wsti.peek().changeEvent(0, timeWorked, w1, EventTypes.WSTI);
						FEL.add(wsti.poll());
					}
					break;
				case 2:
					if (w2.checkForComponents()) {
						wsti.peek().changeEvent(0, timeWorked, w2, EventTypes.WSTI);
						FEL.add(wsti.poll());
					}
					break;
				case 3:
					if (w3.checkForComponents()) {
						wsti.peek().changeEvent(0, timeWorked, w3, EventTypes.WSTI);
						FEL.add(wsti.poll());
					}
					break;
				}
				//Inspector is now open, creates a new INSPI for the inspector to take in a new component
				inpi.peek().changeEvent(0,
						timeWorked, e1.getInsp(), e1.getInsp().getRandom(), EventTypes.INSPI);
				FEL.add(inpi.poll());
				//Inspector no longer busy
				e1.getInsp().setBlocked(false);
				inpe.add((InspectorEvent) event);
			}

			break;
			
		case INSPB:
			//Behavior same as INSBE, but doesn't increment block count
			System.out.println("Exporting From Inspector!");
			InspectorEvent e2 = (InspectorEvent) event;
			if (e2.getInsp().checkFull()) {
				System.out.println("Inspectors Buffers are Full!");
				Event closestEvent = getClosestEvent(EventTypes.WSTI);
				e2.changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(),
						closestEvent.getEventsTime(), e2.getInsp(), e2.getFc(), EventTypes.INSPB);
				event = e2;
				FEL.add(event);
			}

			else {
				int station = findWorkstation(e2.getInsp().addToQueue(e2.getFc()),e2.getFc().getComponentType());
				System.out.println("Added to queue for workstation " + station);
				switch (station) {
				case 1:
					if (w1.checkForComponents()) {
						wsti.peek().changeEvent(0, timeWorked, w1, EventTypes.WSTI);
						FEL.add(wsti.poll());
					}
					break;
				case 2:
					if (w2.checkForComponents()) {
						wsti.peek().changeEvent(0, timeWorked, w2, EventTypes.WSTI);
						FEL.add(wsti.poll());
					}
					break;
				case 3:
					if (w3.checkForComponents()) {
						wsti.peek().changeEvent(0, timeWorked, w3, EventTypes.WSTI);
						FEL.add(wsti.poll());
					}
					break;
				}
				inpi.peek().changeEvent(0,
						timeWorked, e2.getInsp(), e2.getInsp().getRandom(), EventTypes.INSPI);
				FEL.add(inpi.poll());
				inspb.add((InspectorEvent) event);
			}

			break;

		case WSTI:
			System.out.println("Importing From Buffers!");
			WorkStationEvent e3 = (WorkStationEvent) event;
			//Check to see if workstation is busy
			if (e3.getWrkst().getisBusy()) {
				//If busy then try again after the closest workstation export event, as then the workstation may no longer be busy.
				System.out.println("Workstation is Busy!");
				Event closestEvent = getClosestEvent(EventTypes.WSTE);
				e3.changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(), closestEvent.getEventsTime(),
						e3.getWrkst(), EventTypes.WSTI);
				event = e3;
				FEL.add(event);
			} else {
				//If not busy then set workstation to busy
				e3.getWrkst().setBusy(true);
				//Take the components from their queues
				e3.getWrkst().removefromQueues(e3.getWrkst().getDesignatedComponents());
				workStation wkstchk = e3.getWrkst();
				//Generate the time for the workstation to create the part
				double generatedTime = wkstchk.generateWorkstationTime();
				switch(wkstchk.getId()) {
				case(1):
					w1times.add(generatedTime);
					break;
				case(2):
					w2times.add(generatedTime);
					break;
				case(3):
					w3times.add(generatedTime);
					break;
				}
				//Create the export event based off the time it takes to create the part
				wste.peek().changeEvent(generatedTime, timeWorked, e3.getWrkst(),EventTypes.WSTE);
				FEL.add(wste.poll());
				wsti.add((WorkStationEvent) event);
			}
			break;

		case WSTE:
			System.out.println("Exporting From Buffers!");
			WorkStationEvent e4 = (WorkStationEvent) event;
			timeWorked = e4.getEventfTime();
			//Increase the amount of products made
			productsCreated++;
			wste.add((WorkStationEvent) event);
			//Workstation no longer busy
			e4.getWrkst().setBusy(false);
			break;
		}

	}
	/**
	 * Prints out each of the queue buffers
	 */
	public static void printBuffers() {
		System.out.print(w1_1.toString() + " ");
		System.out.print(w2_1.toString() + " ");
		System.out.print(w2_2.toString() + " ");
		System.out.print(w3_1.toString() + " ");
		System.out.print(w3_2.toString() + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Initialize();
		System.out.println(checkTime());
		while(!checkTime() || FEL.isEmpty()) {
			printBuffers();
			System.out.println(FEL.toString());
			Event e = retreiveClosestEvent();
			processEvent(e);			
		}
		System.out.println("Products Created: " + productsCreated);
		System.out.println("Number of Blocks: " + inspectorBlockCount);
		System.out.println("Inspector 1 Generated Times " + i1times);
		System.out.println("Inspector 2 Generated Times for Component 2" + i2_2times);
		System.out.println("Inspector 2 Generated Times for Component 3" + i2_3times);
		System.out.println("Workstation 1 Generated Times " + w1times);
		System.out.println("Workstation 2 Generated Times " + w2times);
		System.out.println("Workstation 3 Generated Times " + w3times);
	}
	

}
