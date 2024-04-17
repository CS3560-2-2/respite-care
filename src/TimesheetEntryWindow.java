import java.awt.BorderLayout;
import java.time.LocalDate;
import javax.swing.*;

public class TimesheetEntryWindow extends JPanel {
	JTextField textEntryDate;
	JTextField textStartTime;
	JTextField textEndTime;
	JTextField textNotes;
	JComboBox comboServiceOrder;
	
	// TODO: Get this data from somewhere else
	Integer[] assignedServiceOrders = {1111, 1732, 1443};
	String entryDate = "4/1/2024";
	String startTime = "10:25AM";
	String endTime = "4:45PM";
	int serviceOrderIndex = 1;
	String notes = "Very needy on Mondays...";
	
	// Called when creating a new entry
	public TimesheetEntryWindow(String employeeName) {
		this(employeeName, -1);
	}
	
	// Called when updating an existing entry
	public TimesheetEntryWindow(String employeeName, int entryID) {
		// Labels (text)
		JLabel labTimeEntry = new JLabel("Add Entry Page");
		labTimeEntry.setBounds(25, 0, 200, 40);
		JLabel labEmployee = new JLabel("Adding Timetable Entry For: " + employeeName);
		labEmployee.setBounds(25, 25, 500, 40);
		JLabel labEntryDate = new JLabel("Entry Date: ");
		labEntryDate.setBounds(25, 50, 200, 40);
		JLabel labStartTime = new JLabel("Start Time: ");
		labStartTime.setBounds(25, 75, 200, 40);
		JLabel labEndTime = new JLabel("End Time: ");
		labEndTime.setBounds(25, 100, 200, 40);
		JLabel labServiceOrder = new JLabel("Service Order: ");
		labServiceOrder.setBounds(25, 125, 200, 40);
		JLabel labNotes = new JLabel("Notes: ");
		labNotes.setBounds(25, 150, 200, 40);
		
		// Text input fields
		LocalDate currentDate = java.time.LocalDate.now();
		int month = currentDate.getMonthValue();
		int day = currentDate.getDayOfMonth();
		int year = currentDate.getYear();
		textEntryDate = new JTextField(month + "/" + day + "/" + year);
		textEntryDate.setBounds(150, 50, 300, 40);
		textStartTime = new JTextField();
		textStartTime.setBounds(150, 75, 300, 40);
		textEndTime = new JTextField();
		textEndTime.setBounds(150, 100, 300, 40);
		textNotes = new JTextField();
		textNotes.setBounds(150, 150, 300, 300);
		
		// Combobox (dropdown menu)
		comboServiceOrder = new JComboBox(assignedServiceOrders);
		comboServiceOrder.setBounds(150, 125, 300, 40);
		
		// If we've been given an entry ID, pre-populate the fields with the entry's data
		if (entryID != -1)
		{
			// TODO: Get all this data from the database instead
			textEntryDate.setText(entryDate);
			textStartTime.setText(startTime);
			textEndTime.setText(endTime);
			comboServiceOrder.setSelectedIndex(serviceOrderIndex);
			textNotes.setText(notes);
		}
		
		// Buttons
		JButton butUpdate = new JButton("Update Entry");
		butUpdate.addActionListener(e -> {submitEntryInfo();});
		JButton butDelete = new JButton("Delete Entry");
		butDelete.addActionListener(e -> {deleteEntry(entryID);});
		JButton butCancel = new JButton("Cancel");
		butCancel.addActionListener(e -> {Main.previousPanel();});
		if (entryID == -1)
		{
			butUpdate.setText("Add Entry");
		}
		
		
		// Add the items to the frame
		this.add(labTimeEntry);
		this.add(labEmployee);
		this.add(labEntryDate);
		this.add(labStartTime);
		this.add(labEndTime);
		this.add(labServiceOrder);
		this.add(labNotes);
		this.add(textEntryDate);
		this.add(textStartTime);
		this.add(textEndTime);
		this.add(comboServiceOrder);
		this.add(textNotes);
		this.add(butUpdate);
		if (entryID == -1) this.add(butDelete);
		this.add(butCancel);
	}

	// Gather the data from the fields and submit them
	// TODO: There's no form of error-checking here! For now we just hope the user plays nice.
	private void submitEntryInfo() {
		// TODO: Maybe use different data types for these values?
		String entryDate = textEntryDate.getText();
		String startTime = textStartTime.getText();
		String endTime = textEndTime.getText();
		// TODO: Refer to the database service orders here
		int serviceOrder = assignedServiceOrders[comboServiceOrder.getSelectedIndex()];
		String notes = textEntryDate.getText();
		
		// TODO: Submit the new/updated info into the database somehow
		System.out.println("Timesheet entry submitted!");
		// Return to the timesheet panel
		Main.previousPanel();
	}
	
	// Remove a timesheet entry from the database
	private void deleteEntry(int entryID) {
		// TODO: Auto-generated method stub
		System.out.println("Timesheet entry deleted!");
		// Return to the timesheet panel
		Main.previousPanel();
	}
}
