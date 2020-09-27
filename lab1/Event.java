package lab1;

public class Event {
	
	public static enum EventType{
		ARRIVAL,
		DEPARTURE,
		OBSERVER,
	}
	
	private double event_time;
	private EventType event_type;
	
	public Event(){
	}
	
	public Event(double time, EventType type) {
		event_time = time;
		event_type = type;
	}
	
	public double GetEventTime() {
		return event_time;
	}
	
	public void SetEventTime(double time) {
		event_time = time;
	}
	
	public EventType GetEventType() {
		return event_type;
	}
	
	public void SetEventTime(EventType type) {
		event_type = type;
	}
}
