// Administrator class
// Extends Person class
// Used to store administrator information

public class Administrator extends Person {
	
	// SSN of this administrator (used as key value)
	private String ssn;
	// This administrator's permission level
	private int permissionLevel;
	
	public Administrator(String firstName, String lastName, String ssn) {
		super(firstName, lastName);
		this.ssn = ssn;
		permissionLevel = 0;
	}
	
	// Getters and setters...
	
	public String getSsn() {
		return ssn;
	}
	
	public int getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
}
