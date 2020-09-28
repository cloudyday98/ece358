package lab1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EventQueue {
	
	public static enum QueueType{
		MM1,
		MM1K,
	}
	
	private PriorityQueue<Event> event_queue;
	private Comparator<Event> event_sort;
	private QueueType queue_type;
	private int capacity;
	
	EventQueue() {
		event_sort = Comparator.comparingDouble(Event::GetEventTime);
		event_queue = new PriorityQueue<>(event_sort);
		queue_type = QueueType.MM1; //assume to be infinite
		capacity = Integer.MAX_VALUE;
	}
	
	// passing only the queue assumes that the queue is infinite
	EventQueue(EventQueue queue){
		event_sort = Comparator.comparingDouble(Event::GetEventTime);
		event_queue =  new PriorityQueue<>(event_sort);
		queue_type = queue.GetQueueType();
		capacity = queue.GetQueueCapacity();
		
		for (Event e : queue.GetEventQueue()) event_queue.add(e);

	}
	
	// Member access
	public PriorityQueue<Event> GetEventQueue(){ return event_queue; }	
	public QueueType GetQueueType() { return queue_type; }
	public int GetQueueSize() { return event_queue.size(); }
	public int GetQueueCapacity() {return capacity;}
	
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
		return event_queue.poll();
	}
	
}
