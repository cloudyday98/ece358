package lab1;

import java.util.LinkedList; 
import java.util.Queue;


public class EventGenerator {
	
	public static EventQueue GenerateArrivalEvents(double lambda, int simulation_time) {
		
        double current_time = 0.0;
        double arrival_time = 0.0;
        
        InfiniteEventQueue event_arrivals = new InfiniteEventQueue();

        while (current_time < simulation_time) {
        	
            arrival_time = RandomVariableGenerator.GenerateRandomVariable(lambda);
            current_time += arrival_time;
            Event a = new Event(current_time, Event.EventType.ARRIVAL);
            event_arrivals.AddToEventQueue(a);
        }
        
		return event_arrivals;
	}
	
	public static EventQueue GenerateDepartureEvents(InfiniteEventQueue event_queue, 
			int average_packet_length,
			int transmission_rate) {
		
		
        double service_time = 0.0;
        double current_packet_length = 0.0;
        double departure_time = 0.0;
        
        Queue<Event> new_event_queue =  event_queue.GetEventQueue();
        
        for (Event a : new_event_queue) {
        	
        	current_packet_length = 
        			RandomVariableGenerator.GenerateRandomVariable(average_packet_length);
        	
            service_time = current_packet_length/transmission_rate;

            
            if (a.GetEventTime() <= service_time) 
            	departure_time = service_time + service_time;
            else departure_time = a.GetEventTime() + service_time;


            service_time = departure_time;

            new_event_queue.add(new Event(departure_time, Event.EventType.DEPARTURE));
        
        }
        
        //merge_into_events_queue(departure_events)
		
		return new InfiniteEventQueue(); 
	}
	
	public static EventQueue GenerateDepartureEvents(FiniteEventQueue event_queue, 
			int packet_length,
			int transmission_rate) {
		
		//TODO: generate the departure events for MM1K queue
		
		return new FiniteEventQueue(); 
	}
	
	public static EventQueue GenerateObserverEvents
	(InfiniteEventQueue event_queue, double lambda, int simulation_time) {
		
		double current_time = 0.0;
        
        
        InfiniteEventQueue observer_events = new InfiniteEventQueue();

        while (current_time < simulation_time) {
        	
            current_time += RandomVariableGenerator.GenerateRandomVariable(lambda*5);
            Event a = new Event(current_time, Event.EventType.OBSERVER);
            observer_events.AddToEventQueue(a);
        }
        
		return observer_events;
	}
}
