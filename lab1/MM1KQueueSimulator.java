package lab1;

public class MM1KQueueSimulator {
	
	// parameters
	private static final int L = 2000; //average packet length
	private static final int C = 1000000;
	private static final int T = 1000;
	private static final int[] K = {10, 25, 50};
	private static double RHO = 0.5;
	private static double lambda;
	private static int packet_loss;
	
	private static EventQueue event_queue;
	
	public static void main(String[] args) {

		RunSimulation();
		
	}
	
	public static void RunSimulation() {
		
		event_queue = new EventQueue();
		
		// Testing on RHO is from 0.25 to 0.95
		
		while (RHO <= 1.51) {
			
			lambda = RHO*C/L;
			
			for (int k : K) {
				event_queue = EventGenerator.GenerateArrivalEvents(event_queue, lambda, T);

				event_queue = EventGenerator.GenerateDepartureEventsForMM1K(event_queue, L, C, k);
				
				packet_loss = EventGenerator.GetPacketLoss();
				
				event_queue = EventGenerator.GenerateObserverEvents(event_queue, lambda, T);
				
				ProcessEvents(k);
			}
			
			RHO += 0.1;
		}
		
		
	}
	
	public static void ProcessEvents(int buffer_size) {
		
		Event current_event;
		int queue_size = 0;
		int arrival_count = 0;
		long queue_size_sum = 0;
		int observer_count = 0;
		
		while(event_queue.GetQueueSize() != 0) {
			current_event = event_queue.RemoveEvent();
			
			switch (current_event.GetEventType()) {
				
				case ARRIVAL:
					
					arrival_count += 1;
					
					if (queue_size < buffer_size) {
						queue_size += 1;
					}
					
					break;
					
				case DEPARTURE:
					
					queue_size -= 1;
					
					
					break;
					
				case OBSERVER:
					
					observer_count += 1;
					queue_size_sum += queue_size;
					
					break;
			}
		}
		
		double EN = (double)queue_size_sum/(double)observer_count;
		System.out.println("RHO = " + RHO + ", K = " + buffer_size);
		System.out.println("Average Number of Packet: " + EN);
		System.out.println("Percentage of Loss: " + (double)packet_loss/(double)arrival_count);
		System.out.println("");
		
		
	}
}
