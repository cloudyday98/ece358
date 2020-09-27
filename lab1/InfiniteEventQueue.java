package lab1;

import java.util.LinkedList; 
import java.util.Queue; 

public class InfiniteEventQueue {
	
	private Queue<Event> event_queue;
	
	InfiniteEventQueue() {
		event_queue = new LinkedList<>();
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
