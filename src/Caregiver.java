// Caregiver class
// Extends Person class
// Used to store caregiver information

public class Caregiver extends Person {
	
	// SSN of this caregiver (used as key value)
	private String ssn;
	// Timesheets of this caregiver
	private Timesheet[] timesheets;
	// Service orders of this caregiver
	private ServiceOrder[] serviceOrders;
	
	public Caregiver(String firstName, String lastName, String ssn) {
		super(firstName, lastName);
		this.ssn = ssn;
	}
	
	// Add a new timesheet to the list of timesheets
	public void addTimesheet(Timesheet newTimesheet) {
		// TODO: add stuff here
	}
	
	// Add a new service order to the list of service orders
		public void addServiceOrder(ServiceOrder newServiceOrder) {
			// TODO: add stuff here
		}
	
	// Getters and setters...

	public String getSSN() {
		return ssn;
	}
	
	public Timesheet[] getTimesheets() {
		return timesheets;
	}
		
	public void setTimesheets(Timesheet[] timesheets) {
		this.timesheets = timesheets;
	}

	public ServiceOrder[] getServiceOrders() {
		return serviceOrders;
	}

	public void setServiceOrders(ServiceOrder[] serviceOrders) {
		this.serviceOrders = serviceOrders;
	}
}
