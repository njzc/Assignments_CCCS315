public class WaitingCustomerQueue extends CirArrayQueue {
	
	// default constructor
	public WaitingCustomerQueue() {
		super();
	}

	// constructor with parameter queue size
	public WaitingCustomerQueue(int size) {
		super(size);
	}

	// copy constructor
	public WaitingCustomerQueue(WaitingCustomerQueue otherQ) {
		super(otherQ);
	}

	// Method to increment the waiting time of each
	// customer in the queue by one time unit.
	// Postcondition: The waiting time of each customer in
	// the queue is incremented by one time unit.
	public void updateWaitingQueue() {
		
		//create a temp queue;		
		CirArrayQueue tempQueue = new CirArrayQueue();
		
		//increment each customer waiting time by one time unit, 
		//and copy it from self queue to temp queue,
		//then delete it from self queue
		while ( !this.isEmpty() )
		{
			Customer customer = (Customer)this.peekFront();
			if ( customer != null )
			{
				customer.incrementWaitingTime();
				tempQueue.enqueue(customer);
				this.dequeue();
			}
		}
		
		//copy from temp queue
		super.copyQueue(tempQueue);
			
	}
}