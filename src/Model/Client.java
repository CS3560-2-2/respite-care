package Model;// Model.Client class
// Extends Model.Person class
// Used to store client information

public class Client extends Person {
	
	// Medical number of this client (used as key value)
	private int medicalNumber;
	// Service orders related to this client
	private ServiceOrder[] serviceOrders;
	
	public Client(String firstName, String lastName, int medicalNumber) {
		super(firstName, lastName);
		this.medicalNumber = medicalNumber;

	}
	
	// Add a new service order to the list of service orders
	public void addServiceOrder(ServiceOrder newServiceOrder) {
		// TODO: add stuff here
	}
	
	// Getters and setters...
	
	public int getMedicalNumber() {
		return medicalNumber;
	}

	public ServiceOrder[] getServiceOrders() {
		return serviceOrders;
	}

	public void setServiceOrders(ServiceOrder[] serviceOrders) {
		this.serviceOrders = serviceOrders;
	}
}
