import View.TimesheetEntryPreview;
import lib.MyConnector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class TimesheetWindow extends JPanel {
	JPanel entryPanel;

	// TODO: This should eventually use the Timesheet class instead!
	int[] entryIDs = {123456789, 807648321, 789123456, 987654123, 136816};
	public TimesheetWindow(int employeeID)
	{
		// Takes an employee ID as a parameter, if that ID is passed then the id of the employee is used to query timesheet entries, otherwise all entries are shown
		super();
		this.setLayout(new BorderLayout());

		JPanel topInfoLayout = new JPanel();
		topInfoLayout.setLayout(new BoxLayout(topInfoLayout, BoxLayout.PAGE_AXIS));

		String employeeName = "";

		if (employeeID == -1) {
			employeeName = "Manager";
		} else {
			Map<String, Object> employeeInfo = MyConnector.getList("Person WHERE ssn='" + employeeID + "'").get(0);
			employeeName = employeeInfo.get("firstName") + " " + employeeInfo.get("lastName");
		}

		// Labels (text)
		JLabel labTimeTable = new JLabel("Timetable View");
		JLabel labEmployee = new JLabel("Employee: " + employeeName);

		JButton butAddEntry = new JButton("+ Add Entry");
		butAddEntry.addActionListener(e -> {
			Main.setCurrentPanel(new TimesheetEntryWindow(employeeID));
		});
		JButton butBack = new JButton("Back");
		butBack.addActionListener(e -> {Main.previousPanel();});

		topInfoLayout.add(labTimeTable);
		topInfoLayout.add(labEmployee);
		topInfoLayout.add(butAddEntry);
		topInfoLayout.add(butBack);

		JPanel pageSelectLayout = new JPanel();
//
		//butPrev = new JButton("Prev");
		//butPrev.addActionListener(e -> {changePageOn(-1);});
		//butPrev.setEnabled(false);
		//butNext = new JButton("Next");
		//butNext.addActionListener(e -> {changePageOn(1);});
		//labPages = new JLabel("Page " + (currentPage + 1) + "/" + numPages);
		//labPages.setBounds(25, 300, 500, 40);


		//pageSelectLayout.add(butPrev);
		//pageSelectLayout.add(labPages);
		//pageSelectLayout.add(butNext);


		entryPanel = new JPanel();
		entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.PAGE_AXIS));

		List<Map<String, Object>> timetableEntries = List.of();

		if (employeeID == -1) {
			timetableEntries = MyConnector.getList("Timesheet");
		} else {
			timetableEntries = MyConnector.getList("Timesheet WHERE ssn='" + employeeID + "'");
		}

		for (Map<String, Object> timetableEntry : timetableEntries) {
			entryPanel.add(new TimesheetEntryPreview(
					timetableEntry,
					e -> {
						Main.setCurrentPanel(new TimesheetEntryWindow((long)timetableEntry.get("ssn"), (int)timetableEntry.get("timesheetID")));
						},
					true));
		}

		this.add(topInfoLayout, BorderLayout.BEFORE_FIRST_LINE);
		this.add(new JScrollPane(entryPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

		this.add(pageSelectLayout, BorderLayout.AFTER_LAST_LINE);
		
		//setTimesheetVisibility(ENTRIES_VISIBLE, 0);
		

	}


	
	// Change which timesheet entries are visible depending on the page
	/*
	private void setTimesheetVisibility(int entriesVisible, int startIndex)
	{
		entryPanel.removeAll();

		for(int i = startIndex; i < startIndex + entriesVisible && i < entryIDs.length; i++){
			entryPanel.add(
					new TimesheetEntryPreview(entryIDs[i], e -> {}, true)
			);
		}

		entryPanel.validate();
	}


	public void changePageOn(int change) {
		currentPage += change;

		currentPage = Math.max(Math.min(currentPage, numPages - 1), 0);

		System.out.println("Page has been changed to " + currentPage + " after a change of " + change);

		butNext.setEnabled(true);
		butPrev.setEnabled(true);
		if (currentPage <= 0)
		{
			butPrev.setEnabled(false);
		}
		// TODO: Use Timesheet size here!
		if (currentPage >= numPages - 1)
		{
			butNext.setEnabled(false);
		}

		labPages.setText("Page " + (currentPage + 1) + "/" + numPages);
		setTimesheetVisibility(ENTRIES_VISIBLE, currentPage * ENTRIES_VISIBLE);
	}

	*/
}
