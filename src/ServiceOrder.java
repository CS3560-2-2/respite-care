// ServiceOrder class
// Holds information related to client service orders

public class ServiceOrder {
	
	// Service order authorization number (used as a key value)
	private int authNumber;
	// Date that this service order starts (TODO: Change data type?)
	private String startDate;
	// Date that this service order ends
	private String endDate;
	// Total number of hours allowed
	private int totalHours;
	// Maximum billable amount
	private int maxBillableAmount;
	// Name of the caseworker who submitted this service order
	private String caseWorkerName;
	// Additional notes
	private String notes;
	// Service code of this order
	private ServiceCode serviceCode;
	
	public ServiceOrder(int authNumber, String caseWorkerName, String startDate, String endDate, int totalHours, int maxBillableAmount, ServiceCode serviceCode) {
		this.authNumber = authNumber;
		this.caseWorkerName = caseWorkerName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalHours = totalHours;
		this.maxBillableAmount = maxBillableAmount;
		this.serviceCode = serviceCode;
	}
	
	// Getters and setters...

	public int getAuthNumber() {
		return authNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public int getMaxBillableAmount() {
		return maxBillableAmount;
	}

	public void setMaxBillableAmount(int maxBillableAmount) {
		this.maxBillableAmount = maxBillableAmount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCaseWorkerName() {
		return caseWorkerName;
	}

	public void setCaseWorkerName(String caseWorkerName) {
		this.caseWorkerName = caseWorkerName;
	}

	public ServiceCode getServiceCode() {
		return serviceCode;
	}
}
