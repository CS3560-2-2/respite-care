import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PortalWindow implements ActionListener{
	JFrame frame;
	JButton butTimeSheets;
	JButton butServiceOrders;
	JButton butSubmitFeedback;
	
	// TODO: Get these from somewhere else
	String userName = "Rea L. User";
	String position = "Manager";
	
	public PortalWindow() {
		frame = new JFrame(); 
        
		// Labels (text)
		JLabel labPortal = new JLabel("Respite Care Portal");
		labPortal.setBounds(25, 0, 500, 40);
		JLabel labUser = new JLabel("Currently Logged In As: " + userName);
		labUser.setBounds(25, 25, 500, 40);
		JLabel labPosition = new JLabel("Position: " + position);
		labPosition.setBounds(25, 50, 500, 40);
		
		// Buttons
		butTimeSheets = new JButton("Time Sheets"); 
		butTimeSheets.setBounds(25, 100, 300, 40);
		butTimeSheets.addActionListener(this);
		butServiceOrders = new JButton("Manage Service Orders"); 
		butServiceOrders.setBounds(25, 150, 300, 40); 
		butServiceOrders.addActionListener(this); // TODO: This does nothing as of now
		butSubmitFeedback = new JButton("Submit Feedback"); 
		butSubmitFeedback.setBounds(25, 200, 300, 40); 
		butSubmitFeedback.addActionListener(this); // TODO: This does nothing as of now
		          
		// Add the buttons and labels to the frame
		frame.add(labPortal);
		frame.add(labUser);
		frame.add(labPosition);
		frame.add(butTimeSheets);
		frame.add(butServiceOrders);
		frame.add(butSubmitFeedback); 
		          
		frame.setSize(600, 400);
		frame.setLayout(null); // Using no layout managers  
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Respite Care Portal");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butTimeSheets)
		{
			// TODO: Make sure only one new window is opened at the same time?
			// TODO: We need an additional window before the timesheet to select the caregiver to edit
			new TimesheetWindow();
		}
	}
}
