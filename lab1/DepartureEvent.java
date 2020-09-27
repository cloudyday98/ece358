package lab1;

public class DepartureEvent extends Event{
	
	private double departure_time;
	
	public DepartureEvent(){
	}
	
	public DepartureEvent(double time){
		departure_time = time;
	}
	
	public double GetArrivalTime() {
		return departure_time;
	}
	
	public void SetArrivalTime(double time) {
		departure_time = time;
	}
}
