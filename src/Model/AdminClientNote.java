package Model;// Model.AdminClientNote
// Used to store information on notes between administrators and clients

public class AdminClientNote extends Note {
	
	// Model.Administrator related to this note
	private Administrator admin;
	// Model.Client related to this note
	private Client client;
	
	public AdminClientNote(int urgency, String text, Administrator admin, Client client) {
		super(urgency, text);
		this.admin = admin;
		this.client = client;
	}
	
	// Getters...

	public Administrator getAdministrator() {
		return admin;
	}

	public Client getClient() {
		return client;
	}
}
