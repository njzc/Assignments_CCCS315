public class CirArrayQueue {
	
	private int maxQueueSize;
	private int count; // number of elements in the queue
	private int queueFront;
	private int queueRear;
	private DataElement[] list; // Array of references to the objects that store queue elements

	private static final int DEFAULT_SIZE = 100;
	
	// default constructor, creates a queue of default size 100
	public CirArrayQueue() {
		this(DEFAULT_SIZE);
	}

	// constructor with a parameter
	public CirArrayQueue(int queueSize) {
		if ( queueSize > 0 )
		{
			maxQueueSize = queueSize;
			list = new DataElement[maxQueueSize];
		}

	}

	// copy constructor
	public CirArrayQueue(CirArrayQueue otherQueue) {
		this();
		copyQueue(otherQueue);
	}

	// Method to initialize the queue to an empty state.
	public void initializeQueue() {
		queueFront = 0;
		queueRear = 0;
		count = 0;
	}

	// Method to determine whether the queue is empty.
	public boolean isEmpty() {
		return count == 0;
	}

	// Method to determine whether the queue is full.
	public boolean isFull() {
		return count == maxQueueSize;
	}

	// Method to return the first element of the queue.
	public DataElement peekFront() {
		if (!isEmpty()) {
			return list[queueFront];
		} else {
			return null;
		}
	}

	// Method to return the last element of the queue.
	public DataElement peekRear() {
		if (!isEmpty()) {
			int lastPosition = ( queueFront + count ) % maxQueueSize;
			return list[lastPosition];
		} else {
			return null;
		}
	}

	// Method to add queueElement to the rear of the queue.
	public void enqueue(DataElement queueElement) {
		if ( !isFull())
		{
			list[queueRear] = queueElement;
			queueRear = ( queueRear + 1 ) % maxQueueSize; 
			count++;
		}
		else
		{
			System.out.println("The queue is full.");
		}
	}

	// Method to remove the first element of the queue.
	public void dequeue() {
		if (!isEmpty()) {
			list[queueFront] = null;
			queueFront = ( queueFront + 1 ) % maxQueueSize;
			count--;
		}
		else
		{
			System.out.println("The queue is empty.");
		}
	}

	// Method to make a copy of otherQueue.
	public void copyQueue(CirArrayQueue otherQueue) {
		
		//create a temp queue;		
		CirArrayQueue tempQueue = new CirArrayQueue();
		
		//clear all items from self queue
		while (!this.isEmpty())
		{
			this.dequeue();
		}

		//copy each item from other queue to temp&self queue,
		//then delete it from other queue
		while ( !otherQueue.isEmpty() )
		{
			DataElement item = (DataElement)otherQueue.peekFront();
			if ( item != null )
			{
				tempQueue.enqueue(item);
				this.enqueue(item);
				otherQueue.dequeue();
			}
		}
		
		//re-add items from temp queue to other queue
		while ( !tempQueue.isEmpty())
		{
			DataElement item = (DataElement)tempQueue.peekFront();
			if ( item != null )
			{
				otherQueue.enqueue(item);
				tempQueue.dequeue();
			}
		}
		
	}
	
	public DataElement[] getList1(){
		return list;
	}
}