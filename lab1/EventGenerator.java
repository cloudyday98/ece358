package lab1;


public class EventGenerator {
	
	public static EventQueue GenerateArrivalEvents(EventQueue event_queue, 
			double lambda, int simulation_time) {
		
        double arrival_time = 0.0;
        
        EventQueue event_arrivals = new EventQueue(event_queue);
        
        while (arrival_time < simulation_time) {

            arrival_time +=  RandomVariableGenerator.GenerateRandomVariable(lambda);   
            Event a = new Event(arrival_time, Event.EventType.ARRIVAL);
            event_arrivals.AddToEventQueue(a);
            
        }

		return event_arrivals;
	}
	
	public static EventQueue GenerateDepartureEventsForMM1(EventQueue event_queue, 
			int average_packet_length,
			int transmission_rate) {
		
		
        double service_time = 0.0;
        double departure_time = 0.0;
        double packet_length = 0.0;
        EventQueue new_event_queue = new EventQueue(event_queue);
        
        for (Event e : event_queue.GetEventQueue()) {
        	
        	packet_length = 
        			RandomVariableGenerator.GenerateRandomVariable(1/(double)average_packet_length);
            service_time = packet_length/transmission_rate;
            if (e.GetEventTime() <= departure_time) departure_time += service_time;
            else departure_time = e.GetEventTime() + service_time;
            
            new_event_queue.AddToEventQueue(new Event(departure_time, Event.EventType.DEPARTURE));
        
        }
		
		return new_event_queue; 
	}
	
	public static EventQueue GenerateDepartureEventsForMM1K(EventQueue event_queue, 
			int packet_length,
			int transmission_rate) {
		
		//TODO: generate the departure events for MM1K queue
		
		return new EventQueue(); 
	}
	
	public static EventQueue GenerateObserverEvents
	(EventQueue event_queue, double lambda, int simulation_time) {
		
		double current_time = 0.0;
        EventQueue new_event_queue = new EventQueue(event_queue);

        while (current_time < simulation_time) {
        	
            current_time += RandomVariableGenerator.GenerateRandomVariable(lambda*5);
            Event a = new Event(current_time, Event.EventType.OBSERVER);
            new_event_queue.AddToEventQueue(a);
        }
        
		return new_event_queue;
	}
}
