import lib.MyConnector;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.util.Random;

public class TimesheetEntryWindow extends JPanel {
	JTextField textEntryDate;
	JTextField textStartTime;
	JTextField textEndTime;
	JTextArea textNotes;
	JComboBox comboServiceOrder;
	
	// TODO: Get this data from somewhere else
	Integer[] assignedServiceOrders = {100109, 1732, 1443};
	String entryDate = "4/1/2024";
	String startTime = "10:25AM";
	String endTime = "4:45PM";
	int serviceOrderIndex = 1;
	String notes = "Very needy on Mondays...";
	
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

		JLabel labEmployee = new JLabel("Creating Timetable Entry For: " + employeeID);
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


		JPanel entryNotesPanel = new JPanel(new BorderLayout());
		JLabel labNotes = new JLabel("Notes: ");
		labNotes.setHorizontalAlignment(JLabel.LEFT);
		textNotes = new JTextArea(4, 50);
		textNotes.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textNotes);
		entryNotesPanel.add(labNotes, BorderLayout.BEFORE_FIRST_LINE);
		entryNotesPanel.add(scrollPane, BorderLayout.CENTER);
		entryDataPanel.add(entryNotesPanel);

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
			textNotes.setText("");
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
	private void submitEntryInfo(int serviceOrderID, long employeeID) {
		if(serviceOrderID == -1) {
			Random rand = new Random();
			serviceOrderID = rand.nextInt();
		}

		// TODO: Maybe use different data types for these values?
		String entryDate = textEntryDate.getText();
		String startTime = textStartTime.getText();
		String endTime = textEndTime.getText();
		// TODO: Refer to the database service orders here
		int serviceOrder = assignedServiceOrders[comboServiceOrder.getSelectedIndex()];
		String notes = textEntryDate.getText();

		MyConnector.insertIntoTable("Timesheet", List.of(
				"" + serviceOrderID,
				"" + serviceOrder,
				"" + employeeID,
				entryDate + " " + startTime + ":00",
				entryDate + " " + endTime + ":00"
		));

		// TODO: Submit the new/updated info into the database somehow
		System.out.println("Timesheet entry submitted!");
		// Return to the timesheet panel
		Main.previousPanel();
	}
	
	// Remove a timesheet entry from the database
	private void deleteEntry(long entryID) {
		// TODO: Auto-generated method stub
		System.out.println("Timesheet entry deleted!");
		// Return to the timesheet panel
		Main.previousPanel();
	}
}
