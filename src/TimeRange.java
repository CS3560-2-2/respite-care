// TimeRange class
// Holds information about a duration of time

public class TimeRange {

	// Unique key identifier number
	private int id;
	// Time that this range starts (TODO: Change data type?)
	private String startTime;
	// Time that this range ends
	private String endTime;
	// The service order that this time range is related to
	private ServiceOrder serviceOrder;

	public TimeRange(String startTime, String endTime, ServiceOrder serviceOrder) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.serviceOrder = serviceOrder;
		
		// TODO: Create method for generating unique IDs
		// id = ...
	}
	
	// Getters...

	public int getId() {
		return id;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
	
	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}
}
