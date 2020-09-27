package lab1;

public class ArrivalEvent extends Event{
	
	private double arrival_time;
	
	public ArrivalEvent(){
	}
	
	public ArrivalEvent(double time){
		arrival_time = time;
	}
	
	public double GetArrivalTime() {
		return arrival_time;
	}
	
	public void SetArrivalTime(double time) {
		arrival_time = time;
	}
}
