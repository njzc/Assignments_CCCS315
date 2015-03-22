public class Server {
	private Customer currentCustomer;
	private String status; // value is "free" or "busy"
	private int transactionTime;

	private final String STATUS_FREE = "free";
	private final String STATUS_BUSY = "busy";

	// default constructor
	public Server() {
		currentCustomer = null;
		status = STATUS_FREE;
		transactionTime = 0;
	}

	// Method to determine whether a server is free.
	public boolean isFree() {
		return status.equals(STATUS_FREE);
	}

	// Method to set the status of a server to "busy".
	public void setBusy() {
		status = STATUS_BUSY;
	}

	// Method to set the status of a server to "free".
	public void setFree() {
		status = STATUS_FREE;
	}

	// Method to set the transaction time according to the parameter t.
	public void setTransactionTime(int t) {
		transactionTime = t;
	}

	// Method to set the transaction time according to customer’s time.
	public void setTransactionTime() {
		transactionTime = currentCustomer.getTransactionTime();
	}

	// Method to return the remaining transaction time.
	public int getRemainingTransactionTime() {
		return transactionTime;
	}

	// Method to decrease the transaction time by 1.
	public void decreaseTransactionTime() {
		if ( transactionTime > 0 )
		{
			transactionTime--;
		}
	}

	// Method to set the current customer info according to cCustomer.
	public void setCurrentCustomer(Customer cCustomer) {
		currentCustomer = cCustomer;
	}

	// Method to return the customer number of the current customer.
	public int getCurrentCustomerNumber() {
		return currentCustomer.getCustomerNumber();
	}

	// Method to return the arrival time of the current customer.
	public int getCurrentCustomerArrivalTime() {
		return currentCustomer.getArrivalTime();
	}

	// Method to return the current waiting time of the current customer.
	public int getCurrentCustomerWaitingTime() {
		return currentCustomer.getWaitingTime();
	}

	// Method to return the transaction time of the current customer.
	public int getCurrentCustomerTransactionTime() {
		return currentCustomer.getTransactionTime();
	}
}