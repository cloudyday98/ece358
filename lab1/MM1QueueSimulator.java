package lab1;

public class MM1QueueSimulator{
	
	// parameters
	private static final int L = 2000; //average packet length
	private static final int C = 1000000;
	private static final int T = 1000;
	private static double RHO = 0.25;
	private static double lambda;
	
	private static EventQueue event_queue;
	
	public static void main(String[] args) {

		RunSimulation();
		
	}
	
	public static void RunSimulation() {
		
		event_queue = new EventQueue();
		
		// Testing on RHO is from 0.25 to 0.95
		
		while (RHO <= 0.95) {
			
			lambda = RHO*C/L;
			
			event_queue = EventGenerator.GenerateArrivalEvents(event_queue, lambda, T);

			event_queue = EventGenerator.GenerateDepartureEventsForMM1(event_queue, L, C);
			
			event_queue = EventGenerator.GenerateObserverEvents(event_queue, lambda, T);
			
			ProcessEvents();
			
			RHO += 0.1; // increment rho
		}
		
		// Testing on RHO equals to 1.2
		
		RHO = 1.2;
		
		lambda = RHO*C/L;
		
		event_queue = EventGenerator.GenerateArrivalEvents(event_queue, lambda, T);

		event_queue = EventGenerator.GenerateDepartureEventsForMM1(event_queue, L, C);
		
		event_queue = EventGenerator.GenerateObserverEvents(event_queue, lambda, T);
		
		ProcessEvents();
		
	}
	
	public static void ProcessEvents() {
		
		Event current_event;
		int queue_size = 0;
		long queue_size_sum = 0;
		double idle_start = 0.0;
		double total_queue_idle_time = 0.0;
		boolean queue_is_idling = false;
		int observer_count = 0;
		
		while(event_queue.GetQueueSize() != 0) {
			current_event = event_queue.RemoveEvent();
			
			switch (current_event.GetEventType()) {
				
				case ARRIVAL:
					
					queue_size += 1;
					
					if (queue_is_idling) {
						queue_is_idling = false;
						total_queue_idle_time += 
								(current_event.GetEventTime() - idle_start);
						idle_start = 0.0;
					}
					
					break;
					
				case DEPARTURE:
					
					queue_size -= 1;
					
					if (queue_size == 0) {
						if (!queue_is_idling) {
							queue_is_idling = true;
							idle_start = current_event.GetEventTime();
						}
					}
					
					break;
					
				case OBSERVER:
					
					observer_count += 1;
					queue_size_sum += queue_size;
					
					break;
			}
		}
		
		double EN = (double)queue_size_sum/(double)observer_count;
		System.out.println("Average Number of Packet: " + EN);
		System.out.println("Percentage of Idle: " + total_queue_idle_time/(double)T);
		
	}
	
}

/* the main function below was for testing purpose */	
/*	public static void main(String[] args) {
		
		event_queue = new EventQueue();
		// create Mocking Arrival Event
		Event a = new Event(0.75, Event.EventType.ARRIVAL);
		Event b = new Event(0.02, Event.EventType.ARRIVAL);
		Event c = new Event(0.6, Event.EventType.ARRIVAL);
		Event d = new Event(0.8, Event.EventType.ARRIVAL);
		Event e = new Event(0.55, Event.EventType.ARRIVAL);
		
		//EventQueue q1 = new EventQueue();
		event_queue.AddToEventQueue(a);
		event_queue.AddToEventQueue(b);
		event_queue.AddToEventQueue(c);
		event_queue.AddToEventQueue(d);
		event_queue.AddToEventQueue(e);
		
		
		Event f = new Event(0.86, Event.EventType.DEPARTURE);
		Event g = new Event(0.11, Event.EventType.DEPARTURE);
		Event h = new Event(0.83, Event.EventType.DEPARTURE);
		Event i = new Event(0.91, Event.EventType.DEPARTURE);
		Event j = new Event(0.71, Event.EventType.DEPARTURE);
		
		//EventQueue q2 = new EventQueue(q1);
		event_queue.AddToEventQueue(f);
		event_queue.AddToEventQueue(g);
		event_queue.AddToEventQueue(h);
		event_queue.AddToEventQueue(i);
		event_queue.AddToEventQueue(j);
		
		Event k = new Event(0.0019, Event.EventType.OBSERVER);
		Event l = new Event(0.93, Event.EventType.OBSERVER);
		Event m = new Event(0.36, Event.EventType.OBSERVER);
		Event n = new Event(0.19, Event.EventType.OBSERVER);
		Event o = new Event(0.65, Event.EventType.OBSERVER);
		Event p = new Event(0.36, Event.EventType.OBSERVER);
		Event q = new Event(0.29, Event.EventType.OBSERVER);
		
		event_queue.AddToEventQueue(k);
		event_queue.AddToEventQueue(l);
		event_queue.AddToEventQueue(m);
		event_queue.AddToEventQueue(n);
		event_queue.AddToEventQueue(o);
		event_queue.AddToEventQueue(p);
		event_queue.AddToEventQueue(q);
		
		System.out.println("Queue Size: " + event_queue.GetQueueSize() + " Queue Type: " + event_queue.GetQueueType());
		
		while(event_queue.GetQueueSize() > 0)
		{
			Event ev = event_queue.RemoveEvent();
			System.out.println("Event Type: " + ev.GetEventType() + " Event Time: " + ev.GetEventTime());
		}
		
	}
*/