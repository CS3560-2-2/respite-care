// Attachment class
// Used to hold information about attached files

public class Attachment {
	
	// Unique key identifier number
	private int id;
	// Date that the file was uploaded (TODO: Change data type?)
	private String uploadDate;
	// User-provided description
	private String description;
	// Location of the actual file (TODO: Change data type?)
	private String file;
	
	public Attachment(String file, String description) {
		this.file = file;
		this.setDescription(description);
		// TODO: method for getting the current date
		// uploadDate = ...
		// TODO: method for generating a unique ID
		// id = ...
	}
	// TODO: Another constructor that takes no description? (Only if we want the description to be optional)
	
	// Getters and setters...
	
	public int getId() {
		return id;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile() {
		return file;
	}
}
