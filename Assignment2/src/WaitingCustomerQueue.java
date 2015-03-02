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
		
		DataElement[] list = super.getList();
		for (int i = 0; i < list.length; i++)
		{
			if ( list[i] != null )
			{
				Customer customer = (Customer)list[i];
				customer.incrementWaitingTime();
			}
		}
		
	}
}