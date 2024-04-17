import View.TimesheetEntryPreview;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class TimesheetWindow extends JPanel {

	JButton butAddEntry;
	JButton butNext;
	JButton butPrev;
	JLabel labPages;
	final int TIMESHEETS_PER_PAGE = 3;
	int currentPage;

	int ENTRIES_VISIBLE = 3;

	int numPages = 0;
	JPanel entryPanel;

	// TODO: Get these from somewhere else
	String employeeName = "Rea L. Employee";
	// TODO: This should eventually use the Timesheet class instead!
	int[] entryIDs = {123456789, 807648321, 789123456, 987654123, 136816};
	public TimesheetWindow()
	{
		super();
		currentPage = 0;
		numPages = (int)Math.ceil((float)entryIDs.length / (float)ENTRIES_VISIBLE);
		this.setLayout(new BorderLayout());

		JPanel topInfoLayout = new JPanel();
		topInfoLayout.setLayout(new BoxLayout(topInfoLayout, BoxLayout.PAGE_AXIS));

		// Labels (text)
		JLabel labTimeTable = new JLabel("Timetable View");
		JLabel labEmployee = new JLabel("Employee: " + employeeName);

		butAddEntry = new JButton("+ Add Entry");
		butAddEntry.addActionListener(e -> {});

		topInfoLayout.add(labTimeTable);
		topInfoLayout.add(labEmployee);
		topInfoLayout.add(butAddEntry);

		JPanel pageSelectLayout = new JPanel();

		butPrev = new JButton("Prev");
		butPrev.addActionListener(e -> {changePageOn(-1);});
		butPrev.setEnabled(false);
		butNext = new JButton("Next");
		butNext.addActionListener(e -> {changePageOn(1);});
		labPages = new JLabel("Page " + (currentPage + 1) + "/" + numPages);
		labPages.setBounds(25, 300, 500, 40);


		pageSelectLayout.add(butPrev);
		pageSelectLayout.add(labPages);
		pageSelectLayout.add(butNext);


		entryPanel = new JPanel();
		entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.PAGE_AXIS));



		this.add(topInfoLayout, BorderLayout.BEFORE_FIRST_LINE);
		this.add(entryPanel, BorderLayout.CENTER);
		this.add(pageSelectLayout, BorderLayout.AFTER_LAST_LINE);
		
		setTimesheetVisibility(ENTRIES_VISIBLE, 0);
		

	}
	
	// Change which timesheet entries are visible depending on the page
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


}
