import java.awt.*;
import javax.swing.*;

public class PortalWindow extends JPanel {
	// TODO: Get these from somewhere else
	String userName = "Rea L. User";
	String position = "Manager";
	
	public PortalWindow() {
		super();

		this.setLayout(new BorderLayout());

		// Labels (text)
		JPanel userInfoLayout = new JPanel();
		userInfoLayout.setLayout(new BoxLayout(userInfoLayout, BoxLayout.PAGE_AXIS));


		JLabel labPortal = new JLabel("Respite Care Portal");
		labPortal.setBounds(25, 0, 500, 40);
		JLabel labUser = new JLabel("Currently Logged In As: " + userName);
		labUser.setBounds(25, 25, 500, 40);
		JLabel labPosition = new JLabel("Position: " + position);
		labPosition.setBounds(25, 50, 500, 40);

		userInfoLayout.add(labPortal);
		userInfoLayout.add(labUser);
		userInfoLayout.add(labPosition);

		this.add(userInfoLayout, BorderLayout.BEFORE_FIRST_LINE);



		JPanel buttonLayout = new JPanel();
		buttonLayout.setLayout(new BoxLayout(buttonLayout, BoxLayout.PAGE_AXIS));

		// Buttons
		JButton butTimeSheets = new JButton("Manage Time Sheets");
		butTimeSheets.addActionListener(e -> {
			Main.setCurrentPanel(new TimesheetWindow(-1));
		});
		JButton butServiceOrders = new JButton("Manage Service Orders");
		butServiceOrders.addActionListener(e -> {Main.setCurrentPanel(new ServiceOrderWindow());});
		JButton butManageNotes = new JButton("Manage Notes");
		butManageNotes.addActionListener(e -> {Main.setCurrentPanel(new ManagerClientNoteViewer());});

		JButton butTimeSheetsCWorker = new JButton("Time Sheets");
		butTimeSheetsCWorker.addActionListener(e -> {
			Main.setCurrentPanel(new TimesheetWindow(100000010));
		});

		JButton butSubmitFeedback = new JButton("Submit Feedback");
		butSubmitFeedback.addActionListener(e -> {Main.setCurrentPanel(new ClientNotesView());}); // TODO: This does nothing as of now
		          
		// Add the buttons and labels to the frame

		buttonLayout.add(new JLabel("Manager Use Cases"));
		buttonLayout.add(butTimeSheets);
		buttonLayout.add(butServiceOrders);
		buttonLayout.add(butManageNotes);
		buttonLayout.add(new JLabel("Client Use Cases"));
		buttonLayout.add(butTimeSheetsCWorker);
		buttonLayout.add(new JLabel("Case Worker Use Cases"));
		buttonLayout.add(butSubmitFeedback);



		this.add(buttonLayout, BorderLayout.CENTER);
	}
}
