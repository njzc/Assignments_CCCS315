public class ServerList {
	private int numOfServers;
	private Server[] servers;

	// default constructor to initialize a list of servers
	public ServerList() {
	}

	// constructor to initialize a list of servers specified by num.
	public ServerList(int num) {
		numOfServers = num;
		servers = new Server[num];
		for (int i = 0; i < servers.length; i++)
		{
			servers[i] = new Server();
		}
	}

	// Method to search the list of servers for a free server,
	// return the ID of a free server if found, else return -1.
	public int getFreeServerID() {
		for (int i = 0; i < numOfServers; i++) {
			if (servers[i].isFree() == true) {
				return i;
			}
		}

		return -1;
	}

	// Method to return the number of busy servers.
	public int getNumberOfBusyServers() {

		int numberOfBusyServers = 0;

		for (int i = 0; i < numOfServers; i++) {
			if (servers[i].isFree() == false) {
				numberOfBusyServers++;
			}
		}

		return numberOfBusyServers;
	}

	// Method to set a server to "busy".
	// Postcondition: To serve the customer specified by
	// cCustomer the server specified by serverID is set
	// to "busy", and the transaction time is set according
	// to the parameter tTime.
	public void setServerBusy(int serverID, Customer cCustomer, int tTime) {
		servers[serverID].setCurrentCustomer(cCustomer);
		servers[serverID].setBusy();
		servers[serverID].setTransactionTime(tTime);
	}

	// Method to set a server to busy.
	// Postcondition: To serve the customer specified by
	// cCustomer, the server specified by serverID is set
	// to "busy", and the transaction time is set according
	// to the customer抯 transaction time.
	public void setServerBusy(int serverID, Customer cCustomer) {
		servers[serverID].setCurrentCustomer(cCustomer);
		servers[serverID].setBusy();
		servers[serverID].setTransactionTime();
	}

	// Method to update the transaction time of each busy server.
	// Postcondition: The transaction time of each busy server
	// is decremented by one time unit. If the transaction
	// time of a busy server is reduced to zero, the
	// server is set to "free" and a message indicating which
	// customer was served, together with the customer's
	// departing time, is printed on the screen.
	public void updateServers() {

		for (int i = 0; i < numOfServers; i++)
		{
			Server server = servers[i];
			if ( server.isFree() == false )
			{
				server.decreaseTransactionTime();
				if ( server.getRemainingTransactionTime() == 0 )
				{
					server.setFree();
					
					// Output the following message to the screen:
					// "Server number XXX, Customer number YYY departed clock unit ZZZ�
					System.out.println("Server number " + ( i + 1 )
							+ ", Customer number " + server.getCurrentCustomerNumber()
							+ " departed at clock unit " + (server.getCurrentCustomerArrivalTime() + server.getCurrentCustomerTransactionTime()));
				}
			}
		}
	}
	
	//to be deleted
	public int getLength()
	{
		return servers.length;
	}
	
	public Server getServer(int index)
	{
		return servers[index];
	}
	
}