package lab1;

public class MM1QueueSimulator{
	
	// parameters
	private static final int L = 2000; //average packet length
	private static final int C = 1000000;
	private static final int T = 1000;
	private static double RHO = 0.25;
	private static double lambda;
	
	private static InfiniteEventQueue event_queue;
	
	public static void main(String[] args) {
		
		event_queue = new InfiniteEventQueue();
		
		RunSimulation();
		
	}
	
	public static void RunSimulation() {
		while (RHO <= 0.95) {
			
			lambda = RHO*C/L;
			
			event_queue = EventGenerator.GenerateArrivalEvents(lambda, T);
			
			event_queue = EventGenerator.GenerateDepartureEvents(event_queue, L, C);
			
			event_queue = EventGenerator.GenerateObserverEvents(event_queue, lambda, T);
			
			RHO += 0.1; // increment rho
		}
	}
	
}