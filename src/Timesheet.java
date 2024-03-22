// Timesheet class
// Used to store timesheet information

public class Timesheet {
	
	// Unique key identifier number
	private int id;
	// Month of this timesheet (TODO: Change data type?)
	private int month;
	// Year of this timesheet
	private int year;
	// Cells within this timesheet
	private TimesheetCell[] cells;
	
	public Timesheet(int month, int year) {
		this.month = month;
		this.year = year;
		// TODO: Create method for generating unique IDs
		// id = ...
	}

	// Add a new cell to the list of cells
	public void addCell(TimesheetCell newTimesheetCell) {
		// TODO: add stuff here
	}
	
	// Getters and setters...

	public int getId() {
		return id;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public TimesheetCell[] getCells() {
		return cells;
	}

	public void setCells(TimesheetCell[] cells) {
		this.cells = cells;
	}
}
