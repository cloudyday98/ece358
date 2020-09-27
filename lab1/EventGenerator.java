package lab1;

import java.util.Queue;


public class EventGenerator {
	
	public static EventQueue GenerateArrivalEvents(EventQueue event_queue, 
			double lambda, int simulation_time) {
		
        double current_time = 0.0;
        double arrival_time = 0.0;
        
        EventQueue event_arrivals = new EventQueue(event_queue);

        while (current_time < simulation_time) {
        	
            arrival_time = RandomVariableGenerator.GenerateRandomVariable(lambda);
            current_time += arrival_time;
            Event a = new Event(current_time, Event.EventType.ARRIVAL);
            event_arrivals.AddToEventQueue(a);
        }
        
		return event_arrivals;
	}
	
	public static EventQueue GenerateDepartureEventsForMM1(EventQueue event_queue, 
			int average_packet_length,
			int transmission_rate) {
		
		
        double service_time = 0.0;
        double current_packet_length = 0.0;
        double departure_time = 0.0;
        
        EventQueue new_event_queue = new EventQueue(event_queue);
        
        for (Event e : new_event_queue.GetEventQueue()) {
        	
        	current_packet_length = 
        			RandomVariableGenerator.GenerateRandomVariable(average_packet_length);
        	
            service_time = current_packet_length/transmission_rate;
            
            if (e.GetEventTime() <= service_time) 
            	departure_time = service_time + service_time;
            else departure_time = e.GetEventTime() + service_time;


            service_time = departure_time;

            new_event_queue.AddToEventQueue(new Event(departure_time, Event.EventType.DEPARTURE));
        
        }
		
		return new EventQueue(); 
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
        
        
        EventQueue observer_events = new EventQueue(event_queue);

        while (current_time < simulation_time) {
        	
            current_time += RandomVariableGenerator.GenerateRandomVariable(lambda*5);
            Event a = new Event(current_time, Event.EventType.OBSERVER);
            observer_events.AddToEventQueue(a);
        }
        
		return observer_events;
	}
}
