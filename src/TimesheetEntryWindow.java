import java.time.LocalDate;
import javax.swing.*;

public class TimesheetEntryWindow extends JPanel {
	JTextField textEntryDate;
	JTextField textStartTime;
	JTextField textEndTime;
	JTextField Notes;
	
	public TimesheetEntryWindow(String employeeName) {
		// Labels (text)
		JLabel labTimeEntry = new JLabel("Add Entry Page");
		labTimeEntry.setBounds(25, 0, 200, 40);
		JLabel labEmployee = new JLabel("Adding Timetable Entry For: " + employeeName);
		labEmployee.setBounds(25, 25, 500, 40);
		JLabel labEntryDate = new JLabel("Entry Date: ");
		labEntryDate.setBounds(25, 50, 200, 40);
		JLabel labStartTime = new JLabel("Start Time: ");
		labStartTime.setBounds(25, 75, 200, 40);
		JLabel labEndTime = new JLabel("End Time");
		labEndTime.setBounds(25, 100, 200, 40);
		JLabel labServiceOrder = new JLabel("Service Order: ");
		labServiceOrder.setBounds(25, 125, 200, 40);
		JLabel labNotes = new JLabel("Notes: ");
		labNotes.setBounds(25, 150, 200, 40);
		
		// Text fields
		LocalDate currentDate = java.time.LocalDate.now();
		int month = currentDate.getMonthValue();
		int day = currentDate.getMonthValue();
		int year = currentDate.getMonthValue();
		textEntryDate = new JTextField(month + "/" + day + "/" + year);
		textEntryDate.setBounds(150, 50, 300, 40);
		textStartTime = new JTextField();
		textEndTime = new JTextField();
		Notes = new JTextField();
		
		// Combobox (dropdown menu)
		JComboBox comboServiceOrder;
		
		// Buttons
		JButton butAdd;

		
		// Add the labels and buttons to the frame
		this.add(labTimeEntry);
		this.add(labEmployee);
		this.add(labEntryDate);
		this.add(labStartTime);
		this.add(labEndTime);
		this.add(labServiceOrder);
		this.add(labNotes);
		this.add(textEntryDate);
	}
}