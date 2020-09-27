package lab1;

public class ObserverEvent extends Event{
	
	private double observer_time;
	
	public ObserverEvent(){
	}
	
	public ObserverEvent(double time){
		observer_time = time;
	}
	
	public double GetArrivalTime() {
		return observer_time;
	}
	
	public void SetArrivalTime(double time) {
		observer_time = time;
	}
}
