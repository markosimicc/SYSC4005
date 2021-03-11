import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class model {

	public static int productsCreated, workTime, timeWorked, inspectorBlockCount;
	public static boolean timeEnded;
	public static ArrayList<Event> FEL;
	public static Queue<InspectorEvent> inpi, inpe, inspb;
	public static Queue<WorkStationEvent> wsti, wste;
	public static workStation w1, w2, w3;
	public static Queue<factoryComponent> w1_1,w2_1,w2_2,w3_1,w3_2;

	public static void Initialize() {
		// Initializing Queues for workstations
		w1_1 = new LinkedList<factoryComponent>();
		w2_1 = new LinkedList<factoryComponent>();
		w2_2 = new LinkedList<factoryComponent>();
		w3_1 = new LinkedList<factoryComponent>();
		w3_2 = new LinkedList<factoryComponent>();
		// Initializing Queues of the workstation Queues for the inspector class
		Queue<Queue<factoryComponent>> workstQueuesI1 = new LinkedList<Queue<factoryComponent>>();
		workstQueuesI1.add(w1_1);
		workstQueuesI1.add(w2_1);
		workstQueuesI1.add(w3_1);
		Queue<Queue<factoryComponent>> workstQueuesI2 = new LinkedList<Queue<factoryComponent>>();
		workstQueuesI2.add(w2_2);
		workstQueuesI2.add(w3_2);
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
		// Creating the groups of usable components for each inspector
		ArrayList<factoryComponent> ins1com = new ArrayList<factoryComponent>();
		ins1com.add(cmp1);
		ArrayList<factoryComponent> ins2com = new ArrayList<factoryComponent>();
		ins2com.add(cmp2);
		ins1com.add(cmp3);
		Inspector ins1 = new Inspector(1, ins1com, workstQueuesI1);
		Inspector ins2 = new Inspector(1, ins2com, workstQueuesI2);
		// Create WorkStations
		ArrayList<Queue<factoryComponent>> workstQueuesW1 = new ArrayList<Queue<factoryComponent>>();
		workstQueuesW1.add(w1_1);
		ArrayList<Queue<factoryComponent>> workstQueuesW2 = new ArrayList<Queue<factoryComponent>>();
		workstQueuesW2.add(w2_1);
		workstQueuesW2.add(w2_2);
		ArrayList<Queue<factoryComponent>> workstQueuesW3 = new ArrayList<Queue<factoryComponent>>();
		workstQueuesW3.add(w3_1);
		workstQueuesW3.add(w3_2);
		w1 = new workStation(1, workstQueuesW1,w1com);
		w2 = new workStation(2, workstQueuesW2,w2com);
		w3 = new workStation(3, workstQueuesW3,w3com);
		// Initializing Simulation Variables
		productsCreated = 0;
		workTime = 20;
		timeWorked = 0;
		inspectorBlockCount = 0;
		timeEnded = false;
		// Creating Collection of Events to use
		InspectorEvent INPI1 = new InspectorEvent();
		InspectorEvent INPI2 = new InspectorEvent();
		inpi = new LinkedList<InspectorEvent>();
		inpi.add(INPI1);
		inpi.add(INPI2);

		InspectorEvent INPE1 = new InspectorEvent();
		InspectorEvent INPE2 = new InspectorEvent();
		inpe = new LinkedList<InspectorEvent>();
		inpe.add(INPE1);
		inpe.add(INPE2);

		InspectorEvent INSPB1 = new InspectorEvent();
		InspectorEvent INSPB2 = new InspectorEvent();
		inspb = new LinkedList<InspectorEvent>();
		inspb.add(INSPB1);
		inspb.add(INSPB2);

		WorkStationEvent WSTI1 = new WorkStationEvent();
		WorkStationEvent WSTI2 = new WorkStationEvent();
		WorkStationEvent WSTI3 = new WorkStationEvent();
		wsti = new LinkedList<WorkStationEvent>();
		wsti.add(WSTI1);
		wsti.add(WSTI1);

		WorkStationEvent WSTE1 = new WorkStationEvent();
		WorkStationEvent WSTE2 = new WorkStationEvent();
		WorkStationEvent WSTE3 = new WorkStationEvent();
		wste = new LinkedList<WorkStationEvent>();
		wste.add(WSTE1);
		wste.add(WSTE1);
		// Creating initial events
		InspectorEvent E1 = inpi.poll();
		E1.changeEvent(0, timeWorked, ins1, cmp1, EventTypes.INSPI);
		InspectorEvent E2 = inpi.poll();
		E2.changeEvent(0, timeWorked, ins2, cmp2, EventTypes.INSPI);
		FEL = new ArrayList<Event>();
		FEL.add(E1);
		FEL.add(E2);
	}

	public static Event retreiveClosestEvent() {
		Event closestEvent = FEL.get(0);
		int index = 0;
		for (int i = 1; i < FEL.size(); i++) {
			if (FEL.get(i).getEventfTime() < closestEvent.getEventfTime()) {
				closestEvent = FEL.get(i);
				index = i;
			}
		}
		FEL.remove(index);
		return closestEvent;
	}
	
	public static Event getClosestEvent() {
		Event closestEvent = FEL.get(0);
		for (int i = 1; i < FEL.size(); i++) {
			if (FEL.get(i).getEventfTime() < closestEvent.getEventfTime()) {
				closestEvent = FEL.get(i);
			}
		}
		return closestEvent;
	}

	public static boolean checkTime() {
		return timeWorked >= workTime;
	}

	public static int findWorkstation(Queue<factoryComponent> queue) {
		if (w1.getworkstationQueues().contains(queue)) {
			return 1;
		} else if (w2.getworkstationQueues().contains(queue)) {
			return 2;
		} else if (w3.getworkstationQueues().contains(queue)) {
			return 3;
		} else {
			return 0;
		}
	}

	public static void processEvent(Event event) {
		switch (event.getEventType()) {
		case INSPI:
			System.out.println("Importing to Inspector!");
			InspectorEvent e = (InspectorEvent) event;
			if (e.getInsp().getBlocked()) {
				Event closestEvent = getClosestEvent();
				e.changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(), closestEvent.getEventsTime(),
						e.getInsp(), e.getFc(), EventTypes.INSPI);
				event = e;
				FEL.add(event);
			} else {
				e.getInsp().setBlocked(true);
				inpe.peek().changeEvent(2, timeWorked, e.getInsp(), e.getFc(), EventTypes.INSPE);
				FEL.add(inpe.poll());
				inpi.add((InspectorEvent) event);
			}
			break;

		case INSPE:
			System.out.println("Exporting From Inspector!");
			InspectorEvent e1 = (InspectorEvent) event;
			timeWorked = event.getEventfTime();
			if (e1.getInsp().checkFull()) {
				System.out.println("Inspectors Buffers are Full!");
				Event closestEvent = getClosestEvent();
				inspb.peek().changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(),
						closestEvent.getEventsTime(), e1.getInsp(), e1.getFc(), EventTypes.INSPB);
				FEL.add(inspb.poll());
				inpe.add((InspectorEvent) event);
				inspectorBlockCount++;
			}

			else {
				int station = findWorkstation(e1.getInsp().addToQueue(e1.getFc()));
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
						timeWorked, e1.getInsp(), e1.getFc(), EventTypes.INSPI);
				FEL.add(inpi.poll());
				e1.getInsp().setBlocked(false);
				inpe.add((InspectorEvent) event);
			}

			break;

		case INSPB:
			InspectorEvent e2 = (InspectorEvent) event;
			if (e2.getInsp().checkFull()) {
				Event closestEvent = getClosestEvent();
				e2.changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(),
						closestEvent.getEventsTime(), e2.getInsp(), e2.getFc(), EventTypes.INSPB);
				event = e2;
				FEL.add(event);
			}

			else {
				int station = findWorkstation(e2.getInsp().addToQueue(e2.getFc()));
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
						timeWorked, e2.getInsp(), e2.getFc(), EventTypes.INSPI);
				FEL.add(inpi.poll());
				inspb.add((InspectorEvent) event);
			}

			break;

		case WSTI:
			WorkStationEvent e3 = (WorkStationEvent) event;
			if (e3.getWrkst().getisBusy()) {
				Event closestEvent = getClosestEvent();
				e3.changeEvent(closestEvent.getEventfTime() - closestEvent.getEventsTime(), closestEvent.getEventsTime(),
						e3.getWrkst(), EventTypes.WSTI);
				event = e3;
				FEL.add(event);
			} else {
				e3.getWrkst().setBusy(true);
				e3.getWrkst().removefromQueues(e3.getWrkst().getDesignatedComponents());
				wste.peek().changeEvent(2, timeWorked, e3.getWrkst(),EventTypes.WSTE);
				FEL.add(wste.poll());
				wsti.add((WorkStationEvent) event);
			}
			break;

		case WSTE:
			WorkStationEvent e4 = (WorkStationEvent) event;
			timeWorked = e4.getEventfTime();
			productsCreated++;
			wste.add((WorkStationEvent) event);
			e4.getWrkst().setBusy(false);
			break;
		}

	}
	
	public static void printBuffers() {
		System.out.print(w1_1.size() + " ");
		System.out.print(w2_1.size() + " ");
		System.out.print(w2_2.size() + " ");
		System.out.print(w3_1.size() + " ");
		System.out.print(w3_2.size() + " ");
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
		System.out.println(productsCreated);
		System.out.println(inspectorBlockCount);
		
	}
	

}
