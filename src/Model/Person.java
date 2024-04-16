package Model;// Model.Person class
// Used to store personal information

public class Person {

	// Physical address(es) of this person
	private Address[] addresses;
	// Model.Person's first name
	private String firstName;
	// Model.Person's last name
	private String lastName;
	// Model.Person's phone number (NOTE: do we want to allow multiple of these?)
	private String phoneNumber;
	// Model.Person's email address (NOTE: do we want to allow multiple of these?)
	private String emailAddress;
	// NOTE: No unique key value for Model.Person objects (should this be changed?)

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		phoneNumber = null;
		emailAddress = null;
		addresses = new Address[0]; // Empty array
	}
	// TODO: Potentially add more constructors that take more arguments (such as email and phone numbers)?
	
	// Add a new address to the list of addresses
	public void addAddress(Address newAddress) {
		// TODO: add stuff here
	}
	
	// Getters and setters...
	
	public Address[] getAddresses() {
		return addresses;
	}
	
	public void setAddresses(Address[] addresses) {
		this.addresses = addresses;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
