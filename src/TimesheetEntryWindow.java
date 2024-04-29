import lib.Connector;
import lib.MyConnector;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.util.Random;

public class TimesheetEntryWindow extends JPanel {
	JTextField textEntryDate;
	JTextField textStartTime;
	JTextField textEndTime;
	JComboBox comboServiceOrder;
	
	// TODO: Get this data from somewhere else
	Integer[] assignedServiceOrders = {100109, 1732, 1443};
	String entryDate = "4/1/2024";
	String startTime = "10:25AM";
	String endTime = "4:45PM";
	int serviceOrderIndex = 1;

	
	// Called when creating a new entry
	public TimesheetEntryWindow(long employeeID) {
		this(employeeID, -1);
	}
	
	// Called when updating an existing entry
	public TimesheetEntryWindow(long employeeID, int entryID) {

		Map<String, Object> existingEntryData = Map.of();

		if(entryID != -1) {
			existingEntryData = MyConnector.getList("Timesheet WHERE timesheetID='" + entryID + "'").get(0);
		}


		this.setLayout(new BorderLayout());
		Map<String, Object> employeeFilter = new HashMap<>();
		employeeFilter.put("ssn", employeeID);
		employeeFilter = Connector.getMatchingRows("Person", employeeFilter).get(0);
		JLabel labEmployee = new JLabel("Creating Timetable Entry For: " + employeeFilter.get("firstName") + 
		" " + employeeFilter.get("lastName"));
		this.add(labEmployee, BorderLayout.BEFORE_FIRST_LINE);

		JPanel entryDataPanel = new JPanel();
		entryDataPanel.setLayout(new BoxLayout(entryDataPanel, BoxLayout.PAGE_AXIS));



		JPanel entryDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labEntryDate = new JLabel("Entry Date: ");
		LocalDate currentDate = java.time.LocalDate.now();
		int month = currentDate.getMonthValue();
		int day = currentDate.getDayOfMonth();
		int year = currentDate.getYear();
		textEntryDate = new JTextField(year + "-" + month + "-" + day);
		textEntryDate.setMaximumSize(new Dimension(100, 40));
		entryDatePanel.add(labEntryDate);
		entryDatePanel.add(textEntryDate);
		entryDataPanel.add(entryDatePanel);

		JPanel entryStartPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labStartTime = new JLabel("Start Time: ");
		textStartTime = new JTextField("3:45");
		textStartTime.setColumns(8);
		entryStartPanel.add(labStartTime);
		entryStartPanel.add(textStartTime);
		entryDataPanel.add(entryStartPanel);

		JPanel entryEndPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labEndTime = new JLabel("End Time: ");
		textEndTime = new JTextField("12:30");
		textEndTime.setColumns(8);
		entryEndPanel.add(labEndTime);
		entryEndPanel.add(textEndTime);
		entryDataPanel.add(entryEndPanel);


		JPanel entryServiceOrderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labServiceOrder = new JLabel("Service Order: ");
		comboServiceOrder = new JComboBox(assignedServiceOrders);
		entryServiceOrderPanel.add(labServiceOrder);
		entryServiceOrderPanel.add(comboServiceOrder);
		entryDataPanel.add(entryServiceOrderPanel);


		this.add(entryDataPanel, BorderLayout.CENTER);
		
		// If we've been given an entry ID, pre-populate the fields with the entry's data
		if (entryID != -1)
		{
			String[] startTime = ((LocalDateTime)existingEntryData.get("startTime")).toString().split("T");
			String[] endTime = ((LocalDateTime)existingEntryData.get("endTime")).toString().split("T");

			// TODO: Get all this data from the database instead
			textEntryDate.setText(startTime[0]);
			textStartTime.setText(startTime[1]);
			textEndTime.setText(endTime[1]);
			comboServiceOrder.setSelectedIndex(serviceOrderIndex);
		}
		
		// Buttons
		JButton butUpdate = new JButton("Update Entry");
		butUpdate.addActionListener(e -> {submitEntryInfo(entryID, employeeID);});
		JButton butDelete = new JButton("Delete Entry");
		butDelete.addActionListener(e -> {deleteEntry(entryID);});
		JButton butCancel = new JButton("Cancel");
		butCancel.addActionListener(e -> {Main.previousPanel();});
		if (entryID == -1)
		{
			butUpdate.setText("Add Entry");
		}

		JPanel bottomButtonsPannel = new JPanel();

		bottomButtonsPannel.add(butUpdate);
		bottomButtonsPannel.add(butDelete);
		bottomButtonsPannel.add(butCancel);

		this.add(bottomButtonsPannel, BorderLayout.AFTER_LAST_LINE);



	}

	// Gather the data from the fields and submit them
	// TODO: There's no form of error-checking here! For now we just hope the user plays nice.
	private void submitEntryInfo(int timesheetID, long employeeID) {
		String entryDate = textEntryDate.getText();
		String startTime = textStartTime.getText();
		String endTime = textEndTime.getText();
		int serviceOrder = assignedServiceOrders[comboServiceOrder.getSelectedIndex()];
	
		// Format startTime and endTime into DATETIME format
		String formattedStartTime = entryDate + " " + startTime + ":00";
		String formattedEndTime = entryDate + " " + endTime + ":00";
	
		Map<String, Object> updatedEntryData = new HashMap<>();
		updatedEntryData.put("startTime", formattedStartTime);
		updatedEntryData.put("endTime", formattedEndTime);
		updatedEntryData.put("authNumber", serviceOrder);
		updatedEntryData.put("ssn", employeeID);
	
		if (timesheetID == -1) {
			// Create a new timesheet entry
			// Random rand = new Random(); //Commented out because mysql doesn't should generate key
			// int newTimesheetID = rand.nextInt(1000000); // Generate a random timesheetID
			// updatedEntryData.put("timesheetID", newTimesheetID);
			Connector.insertRow("Timesheet", updatedEntryData);
		} else {
			// Update an existing timesheet entry
			Map<String, Object> oldEntryData = new HashMap<>();
			oldEntryData.put("timesheetID", timesheetID);
			Connector.updateRow("Timesheet", oldEntryData, updatedEntryData);
		}
	
		System.out.println("Timesheet entry submitted!");
		Main.previousPanel();
	}
	
	// Remove a timesheet entry from the database
	private void deleteEntry(long entryID) {
		Map<String, Object> entryData = new HashMap<>();
		entryData.put("timesheetID", entryID);
		Connector.deleteRow("Timesheet", entryData);
	
		System.out.println("Timesheet entry deleted!");
		Main.previousPanel();
	}
}
