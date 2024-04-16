package Model;// Model.TimesheetCell class
// Holds information about on a specific cell within a larger timesheet

public class TimesheetCell {

	// Unique key identifier number
	private int id;
	// Date of this cell (TODO: Change data type?)
	private String date;
	// Time ranges within this cell
	private TimeRange[] times;

	public TimesheetCell(String date) {
		this.date = date;
		// TODO: Create method for generating unique IDs
		// id = ...
	}
	
	// Add a new time range to the list of times
	public void addTimeRange(TimeRange newTimeRange) {
		// TODO: add stuff here
	}
	
	// Getters and setters...

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public TimeRange[] getTimes() {
		return times;
	}

	public void setTimes(TimeRange[] times) {
		this.times = times;
	}

}
