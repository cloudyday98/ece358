package lab1;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;

public class EventGenerator {
	
	private static int event_dropped = 0;
	
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
			int average_packet_length,
			int transmission_rate,
			int max_queue_size) {
		
		event_dropped = 0;
		
		EventQueue new_event_queue = new EventQueue(event_queue);
		PriorityQueue<Event> temp = event_queue.GetEventQueue();
		ArrayList<Event> departure_pending_list = new ArrayList<>();
		double departure_time = 0.0;
		Event current_event;
		
		while(!temp.isEmpty()) {
			current_event = temp.poll();
			
			if (departure_pending_list.isEmpty()) {
				departure_time = current_event.GetEventTime() + 
						RandomVariableGenerator.GenerateRandomVariable(1/(double)average_packet_length)/transmission_rate;
				departure_pending_list.add(new Event(departure_time, Event.EventType.DEPARTURE));
			}
			
			else {
				
				// remove all the packets that can depart before the next arrival event
				while (!departure_pending_list.isEmpty()) {
					
					if (departure_pending_list.get(0).GetEventTime() < current_event.GetEventTime()) {
						new_event_queue.AddToEventQueue(departure_pending_list.get(0));
						departure_pending_list.remove(0);
					}
					
					else {
						break;
					}
					
				}
				
				// if queue is full, the  drop the event
				if (departure_pending_list.size() >= max_queue_size) {
					event_dropped ++;
				}
				
				else {
					
					if (departure_pending_list.isEmpty()) {
						departure_time = current_event.GetEventTime() + 
								RandomVariableGenerator.GenerateRandomVariable(1/(double)average_packet_length)/transmission_rate;
						departure_pending_list.add(new Event(departure_time, Event.EventType.DEPARTURE));
					}
					
					else {
						departure_time = departure_pending_list.get(departure_pending_list.size() - 1).GetEventTime()
								+ RandomVariableGenerator.GenerateRandomVariable(1/(double)average_packet_length)/transmission_rate;
						departure_pending_list.add(new Event(departure_time, Event.EventType.DEPARTURE));
					}

				}
			}

		}
		
		for (Event e : departure_pending_list) {
			new_event_queue.AddToEventQueue(e);
		}
	
		return new_event_queue;
	}
	
	public static int GetPacketLoss(){
		return event_dropped;
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
