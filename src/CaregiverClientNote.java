// CaregiverClientNote
// Used to store information on notes between caregivers and clients

public class CaregiverClientNote extends Note {
	
	// Caregiver related to this note
	private Caregiver caregiver;
	// Client related to this note
	private Client client;
	
	public CaregiverClientNote(int urgency, String text, Caregiver caregiver, Client client) {
		super(urgency, text);
		this.caregiver = caregiver;
		this.client = client;
	}
	
	// Getters...

	public Caregiver getCaregiver() {
		return caregiver;
	}

	public Client getClient() {
		return client;
	}
}
