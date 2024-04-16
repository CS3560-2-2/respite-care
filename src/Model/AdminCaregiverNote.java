package Model;// Model.AdminCaregiverNote
// Used to store information on notes between administrators and caregivers

public class AdminCaregiverNote extends Note {
	
	// Model.Administrator related to this note
	private Administrator admin;
	// Model.Caregiver related to this note
	private Caregiver caregiver;
	
	public AdminCaregiverNote(int urgency, String text, Administrator admin, Caregiver caregiver) {
		super(urgency, text);
		this.admin = admin;
		this.caregiver = caregiver;
	}
	
	// Getters...

	public Administrator getAdministrator() {
		return admin;
	}

	public Caregiver getCaregiver() {
		return caregiver;
	}
}
