package lab1;

import java.util.LinkedList;
import java.util.Queue;

public class FiniteEventQueue extends EventQueue{
	
	private static Queue<Event> event_queue;
	private int size_limit;
	
	FiniteEventQueue() {
		event_queue = new LinkedList<>();
		size_limit = 0;
	}
	
	FiniteEventQueue(InfiniteEventQueue queue, int size_granted){
		event_queue =  new LinkedList<>(queue.GetEventQueue());
		size_limit = size_granted;
	}
	
	public Queue<Event> GetEventQueue(){
		return event_queue;
	}
	
	public boolean AddToEventQueue(Event e) {
		if (event_queue.size() == size_limit) return false;
		event_queue.add(e);
		return true;
	}
	
	public void SetSizeLimit(int limit) {
		size_limit = limit;
	}
	
	public int GetSizeLimit() {
		return size_limit;
	}
	
	public Event RemoveEvent() {
		return event_queue.remove();
	}
	
	public int GetQueueSize() {
		return event_queue.size();
	}
}
