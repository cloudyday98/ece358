package lab1;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {
	
	public static enum QueueType{
		MM1,
		MM1K,
	}
	
	private static Queue<Event> event_queue;
	private static QueueType queue_type;
	private static int capacity;
	
	EventQueue() {
		event_queue = new LinkedList<>();
		queue_type = null;
		capacity = 0;
	}
	
	// passing only the queue assumes that the queue is infinite
	EventQueue(EventQueue queue){
		event_queue =  new LinkedList<>(queue.GetEventQueue());
		queue_type = QueueType.MM1;
		capacity = Integer.MAX_VALUE;
	}
	
	// passing the size implies that the queue is finite
	EventQueue(EventQueue queue, int queue_size){
		event_queue =  new LinkedList<>(queue.GetEventQueue());
		queue_type = QueueType.MM1K;
		capacity = queue_size;
	}
	
	// Member access
	public Queue<Event> GetEventQueue(){ return event_queue; }	
	public QueueType GetQueueType() { return queue_type; }
	public int QueueSize() { return event_queue.size(); }
	
	// Modifiers
	public boolean AddToEventQueue(Event e) {
		
		if (event_queue.size() >= capacity) return false;
		
		event_queue.add(e);
		return true;
	}
	
	public void ConfigureQueue(QueueType type, int size) {
		
		if (type.equals(QueueType.MM1)) {
			queue_type = type;
			capacity = Integer.MAX_VALUE;
		}
		
		else {
			queue_type = type;
			capacity = size;
		}
	}
	
	public Event RemoveEvent() {
		return event_queue.remove();
	}
	

	
}
