// ServiceCode class
// Holds information about service codes

public class ServiceCode {
	
	// Primary service code number
	private int serviceCodeNumber;
	// Secondary service code number
	private int serviceCodeSubnumber;
	// Length of the service code term (TODO: Change data type?)
	private String termLength;
	// Rate of pay
	private int payRate;
	// Terms of the service code
	private String terms;
	// Key identifier made by combining service code number and subnumber
	private int serviceCodeKey;
	
	public ServiceCode(int serviceCodeNumber, int serviceCodeSubnumber, String termLength, int payRate, String terms) {
		this.serviceCodeNumber = serviceCodeNumber;
		this.serviceCodeSubnumber = serviceCodeSubnumber;
		this.termLength = termLength;
		this.payRate = payRate;
		this.terms = terms;
		
		// TODO: method for generating the service code key
		//serviceCodeKey = ...
	}
	
	// Getters...

	public int getServiceCodeNumber() {
		return serviceCodeNumber;
	}

	public int getServiceCodeSubnumber() {
		return serviceCodeSubnumber;
	}

	public String getTermLength() {
		return termLength;
	}

	public int getPayRate() {
		return payRate;
	}

	public String getTerms() {
		return terms;
	}

	public int getServiceCodeKey() {
		return serviceCodeKey;
	}
}
