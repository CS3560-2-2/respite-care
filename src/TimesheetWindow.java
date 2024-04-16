import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimesheetWindow implements ActionListener{
	JFrame frame;
	JLabel[] timesheetLabels;
	JButton[] timesheetButtons;
	JButton butAddEntry;
	JButton butNext;
	JButton butPrev;
	JLabel labPages;
	final int TIMESHEETS_PER_PAGE = 3;
	int currentPage;
	
	// TODO: Get these from somewhere else
	String employeeName = "Rea L. Employee";
	// TODO: This should eventually use the Timesheet class instead!
	String[] dates = {"02/23/2024", "02/24/2024", "02/25/2024", "02/27/2024"};
	String[] serviceOrders = {"01234", "01234", "01234", "05678"};
	String[] times = {"10:30AM - 3:30PM", "11:30AM - 4:30PM", "10:00AM - 5:00PM", "2:30PM - 7:30PM"};
	
	public TimesheetWindow()
	{
		frame = new JFrame();
		currentPage = 0;

		// Labels (text)
		JLabel labTimeTable = new JLabel("Timetable View");
		labTimeTable.setBounds(25, 0, 500, 40);
		JLabel labEmployee = new JLabel("Employee: " + employeeName);
		labEmployee.setBounds(25,25,500, 40);
		labPages = new JLabel("Page " + (currentPage + 1) + "/" + (dates.length / TIMESHEETS_PER_PAGE + 1));
		labPages.setBounds(25, 300, 500, 40);
		
		// Buttons
		butPrev = new JButton("Prev");
		butPrev.setBounds(100, 310, 75, 20);
		butPrev.addActionListener(this);
		butPrev.setEnabled(false);
		butNext = new JButton("Next");
		butNext.setBounds(200, 310, 75, 20);
		butNext.addActionListener(this);
		butAddEntry = new JButton("+ Add Entry");
		butAddEntry.setBounds(450, 10, 100, 50);
		butAddEntry.addActionListener(this);

		
		// Add the labels and buttons to the frame
		frame.add(labTimeTable);
		frame.add(labEmployee);
		frame.add(labPages);
		frame.add(butPrev);
		frame.add(butNext);
		frame.add(butAddEntry);
		
		// TODO: Use an employee's Timesheet here instead!
		timesheetLabels = new JLabel[dates.length];
		timesheetButtons = new JButton[dates.length];
		for (int i = 0; i < dates.length; i++)
		{
			JLabel newTimesheetLabel = new JLabel("<html>Date: " + dates[i] + "<br>"
					+ "Service Order #: " + serviceOrders[i] + "<br>"
					+ "Time: " + times[i]);
			newTimesheetLabel.setBounds(0, 75 + ((i % TIMESHEETS_PER_PAGE) * 75), 600, 50);
			newTimesheetLabel.setOpaque(true);
			newTimesheetLabel.setBackground(Color.lightGray);
			
			JButton newTimesheetButton = new JButton("Edit Entry");
			newTimesheetButton.setBounds(450, 75 + ((i % TIMESHEETS_PER_PAGE) * 75), 100, 50);
			
			frame.add(newTimesheetLabel);
			frame.add(newTimesheetButton);
			timesheetLabels[i] = newTimesheetLabel;
			timesheetButtons[i] = newTimesheetButton;
		}
		
		setTimesheetVisibility();
		
		frame.setSize(600, 400);
		frame.setLayout(null); // Using no layout managers  
		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Timesheets");
	}
	
	// Change which timesheet entries are visible depending on the page
	private void setTimesheetVisibility()
	{
		// TODO: Use Timesheet size here instead!
		for (int i = 0; i < dates.length; i++)
		{
			if (i >= currentPage * TIMESHEETS_PER_PAGE && i < (currentPage + 1) * TIMESHEETS_PER_PAGE)
			{
				timesheetLabels[i].setVisible(true);
				timesheetButtons[i].setVisible(true);
			}
			else
			{
				timesheetLabels[i].setVisible(false);
				timesheetButtons[i].setVisible(false);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butNext || e.getSource() == butPrev)
		{
			if (e.getSource() == butNext)
			{
				currentPage++;
			}
			else
			{
				currentPage--;
			}
			
			butNext.setEnabled(true);
			butPrev.setEnabled(true);
			if (currentPage <= 0)
			{
				butPrev.setEnabled(false);
			}
			// TODO: Use Timesheet size here!
			if (currentPage >= (dates.length / TIMESHEETS_PER_PAGE))
			{
				butNext.setEnabled(false);
			}
			
			labPages.setText("Page " + (currentPage + 1) + "/" + (dates.length / TIMESHEETS_PER_PAGE + 1));
			setTimesheetVisibility();
		}
		else if (e.getSource() == butAddEntry)
		{
			// Open a new window to create a new Timesheet entry
			// TODO: Get the employee name from database
			new TimesheetEntryWindow(employeeName);
		}
	}
}
