// Note class
// Used to store basic information about notes

public class Note {
	
	// Unique key identifier number
	private int id;
	// Date that the note was created (TODO: Change data type?)
	private String date;
	// Urgency of the note (greater is more urgency)
	private int urgency;
	// User-provided text (TODO: Do we want this to be an array of Strings instead?)
	private String text;
	// Location of the actual file (TODO: Change data type?)
	private Attachment[] attachments;
	
	public Note(int urgency, String text) {
		this.setUrgency(urgency);
		this.setText(text);
		// TODO: method for getting the current date
		// date = ...
		// TODO: method for generating a unique ID
		// id = ...
		setAttachments(new Attachment[0]); // Empty array
	}
	
	// Add a new attachment to the list of attachments
	public void addAttachment(Attachment newAttachment) {
		// TODO: add stuff here
	}
	
	// Getters and setters...

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public int getUrgency() {
		return urgency;
	}

	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Attachment[] getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachment[] attachments) {
		this.attachments = attachments;
	}
}
