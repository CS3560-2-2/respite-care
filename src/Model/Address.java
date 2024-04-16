package Model;// Model.Address class
// Used to store address information

public class Address {

	// Name of the street
	private String street;
	// Name of the city
	private String city;
	// Name of the state
	private String state;
	// Postal code number
	private int postalCode;
	// Name of the country
	private String country;
	// Unique key identifier number
	private int id;

	public Address(String street, String city, String state, int postalCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;

		// TODO: Create method for generating unique address IDs
		// id = ...
	}

	// Basic getters...
	
	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	public int getId() {
		return id;
	}
}
