package lab1;

import java.util.LinkedList; 
import java.util.Queue; 

public class InfiniteEventQueue extends EventQueue{
	
	private static Queue<Event> event_queue;
	
	InfiniteEventQueue() {
		event_queue = new LinkedList<>();
	}
	
	InfiniteEventQueue(InfiniteEventQueue queue){
		event_queue =  new LinkedList<>(queue.GetEventQueue());
	}
	
	public Queue<Event> GetEventQueue(){
		return event_queue;
	}
	
	public void AddToEventQueue(Event e) {
		event_queue.add(e);
	}
	
	public Event RemoveEvent() {
		return event_queue.remove();
	}
	
	public int QueueSize() {
		return event_queue.size();
	}
	
}
