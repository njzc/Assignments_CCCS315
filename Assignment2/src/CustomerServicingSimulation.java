import java.io.IOException;
import java.util.Scanner;
import java.util.TooManyListenersException;

public class CustomerServicingSimulation {
	private static int simulationTime;
	private static int numberOfServers;
	private static int transactionTime;
	private static int timeBetweenCustomerArrival;

	public static void main(String[] args) throws IOException {
		setSimulationParameters();
		runSimulation();
	}

	public static void setSimulationParameters() {
		Scanner in = new Scanner(System.in);

		// Read in and store the simulation time.
		do 
		{
			System.out.print("Enter the simulation time: ");
			simulationTime = in.nextInt();
		} while (simulationTime <= 0 );
		
		
		// Read in and store the number of servers.
		do 
		{
			System.out.print("Enter the number of servers: ");
			numberOfServers = in.nextInt();
		} while (numberOfServers <= 0 );
		
		// Read in and store the transaction time.
		do 
		{
			System.out.print("Enter the transaction time: ");
			transactionTime = in.nextInt();
		} while (transactionTime <= 0 );
		
		// Read in and store the time between customer arrivals.
		do 
		{
			System.out.print("Enter the time between customer arrivals: ");
			timeBetweenCustomerArrival = in.nextInt();
		} while (timeBetweenCustomerArrival <= 0 );
		
		in.close();
	}

	public static boolean isCustomerArrived(double arvTimeDiff) {
		double value;
		value = (double) (Math.random());
		return (value > Math.exp(-1.0 / arvTimeDiff));
	}

	public static void runSimulation() {
		
		// Declare and initialize the variables such as the simulation
		// parameters, customer number, clock,
		// total and average waiting times, number of customers arrived, number
		// of customers served,
		// number of customers left in the waiting queue, number of customers
		// left with the servers, the
		// waiting queue, a list of serves

		int clock = 0;
		int numberOfCustomers = 0;
		int totalWaitingTimes = 0;
		double averageWaitingTimes = 0;
		int numberOfCustomersServed = 0;
		int numberOfCustomersLeftWithServers = 0;
		int numberOfCustomersLeftInQueue = 0;
		
		WaitingCustomerQueue customerQueue = new WaitingCustomerQueue();
		customerQueue.initializeQueue();
		
		ServerList servers = new ServerList(numberOfServers);
		
		for (clock = 1; clock <= simulationTime; clock++) {
			
			// Update the server list to decrement the transaction time of each
			// busy server by one time unit.
			servers.updateServers();
			
			// If the customer's queue is nonempty, increment the waiting time
			// of each customer by one time unit.
			if ( !customerQueue.isEmpty() )
			{
				customerQueue.updateWaitingQueue();
				totalWaitingTimes++;
			}
			
			if (isCustomerArrived(timeBetweenCustomerArrival)) {
				
				// A customer just arrives, increment the number of customers by
				// 1 and add the new customer to the queue.
				numberOfCustomers++;
				Customer newCustomer = new Customer(numberOfCustomers, clock 
						,0 ,transactionTime);
				customerQueue.enqueue(newCustomer);

				// Output the following message to the screen:
				// "Customer number XXX arrived at time unit YYY"
				System.out.println("Customer number " + numberOfCustomers 
						+ " arrived at time unit " + clock);

			}
			
			// If a server is free and the customers' queue is nonempty then
			// remove a customer from the
			// front of the queue and send the customer to the free server.
			int freeServerID = servers.getFreeServerID();
			if ( freeServerID >= 0 )
			{
				Customer currentCustomer = (Customer) customerQueue.peekFront();
				if ( currentCustomer != null )
				{
					customerQueue.dequeue();
					servers.setServerBusy(freeServerID, currentCustomer);
				}
			}
			
		}
		
		//get number of customers left in the waiting queue
		if ( !customerQueue.isEmpty())
		{
			WaitingCustomerQueue tempQueue = new WaitingCustomerQueue(customerQueue);
			while (!tempQueue.isEmpty())
			{
				tempQueue.peekFront();
				numberOfCustomersLeftInQueue++;
				tempQueue.dequeue();
			}
		}
		
		//get average waiting times
		averageWaitingTimes = (double)totalWaitingTimes / numberOfCustomers;
		
		//get numbers of customers left in servers
		numberOfCustomersLeftWithServers = servers.getNumberOfBusyServers();
		
		//get numbers of served customers
		numberOfCustomersServed = numberOfCustomers - numberOfCustomersLeftInQueue - numberOfCustomersLeftWithServers;
		
		// Print the following summary results of the simulation to the screen:
		System.out.println("Simulation ran for " + simulationTime + " time units");
		System.out.println("Number of servers: " + numberOfServers);
		System.out.println("Average transaction time: " + transactionTime);
		System.out.println("Average arrival time difference between customers: " + timeBetweenCustomerArrival);
		System.out.println("Total wait time of all customers: " + totalWaitingTimes);
		System.out.println("Number of customers who completed a transaction: " + numberOfCustomersServed);
		System.out.println("Number of customers left in the servers: " + numberOfCustomersLeftWithServers);
		System.out.println("Number of customers left in the queue: " + numberOfCustomersLeftInQueue);
		System.out.printf("Average wait time: %.2f \r\n",averageWaitingTimes);
		System.out.println("************** END SIMULATION *************");
	}
}