public class Customer extends DataElement {
	private int customerNumber;
	private int arrivalTime;
	private int waitingTime;
	private int transactionTime;

	// default constructor
	public Customer() {
	}

	// constructor to initialize the data members
	public Customer(int custN, int aTime, int wTime, int tTime) {
		customerNumber = custN;
		arrivalTime = aTime;
		waitingTime = wTime;
		transactionTime = tTime;
	}

	// Method to set the data members according to the parameters
	public void setCustomerInfo(int custN, int aTime, int wTime, int tTime) {
		customerNumber = custN;
		arrivalTime = aTime;
		waitingTime = wTime;
		transactionTime = tTime;
	}

	// Method to return the waiting time of a customer.
	public int getWaitingTime() {
		return waitingTime;
	}

	// Method to set the waiting time of a customer.
	public void setWaitingTime(int time) {
		waitingTime = time;
	}

	// Method to increment the waiting time.
	public void incrementWaitingTime() {
		waitingTime++;
	}

	// Method to return the arrival time of a customer.
	public int getArrivalTime() {
		return arrivalTime;
	}

	// Method to return the transaction time of a customer.
	public int getTransactionTime() {
		return transactionTime;
	}

	// Method to return the customer number.
	public int getCustomerNumber() {
		return customerNumber;
	}

	public boolean equals(DataElement otherElement) {
		Customer other = (Customer)otherElement;
		return ( this.arrivalTime == other.getArrivalTime() 
				&& this.customerNumber == other.customerNumber
				&& this.transactionTime == other.transactionTime
				&& this.waitingTime == other.waitingTime);
	}

	public int compareTo(DataElement otherElement) {
		return 0;
	}

	public void makeCopy(DataElement otherElement) {
		Customer other = (Customer)otherElement;
		setCustomerInfo(other.getCustomerNumber(), other.getArrivalTime(), other.getWaitingTime(), other.getTransactionTime());
	}

	public DataElement getCopy() {
		return new Customer(customerNumber, arrivalTime, waitingTime, transactionTime);
	}
}