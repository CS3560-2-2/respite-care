import java.awt.*;
import java.util.Locale;
import javax.swing.*;

public class PortalWindow extends JPanel {
	
	public PortalWindow(String[] args) {
		super();

		this.setLayout(new BorderLayout());

		// Labels (text)
		JPanel userInfoLayout = new JPanel();
		userInfoLayout.setLayout(new BoxLayout(userInfoLayout, BoxLayout.PAGE_AXIS));
		userInfoLayout.add(new JLabel("Respite Care Management System"));

		this.add(userInfoLayout, BorderLayout.BEFORE_FIRST_LINE);



		JPanel buttonLayout = new JPanel();
		buttonLayout.setLayout(new BoxLayout(buttonLayout, BoxLayout.PAGE_AXIS));

		// Buttons
		JButton butTimeSheets = new JButton("Manage Time Sheets");
		butTimeSheets.addActionListener(e -> {
			Main.setCurrentPanel(() -> new TimesheetWindow(-1));
		});
		JButton butServiceOrders = new JButton("Manage Service Orders");
		butServiceOrders.addActionListener(e -> {Main.setCurrentPanel(() -> new ServiceOrderWindow());});
		JButton butManageNotes = new JButton("Manage Notes");
		butManageNotes.addActionListener(e -> {Main.setCurrentPanel(() -> new ManagerClientNoteViewer());});

		JButton butManageCaseworkers = new JButton("Manage Caseworkers");
		butManageCaseworkers.addActionListener(e -> {Main.setCurrentPanel(() -> new CaseworkerManagementWindow());});

		JButton butManageServices = new JButton("Manage Services");
		butManageServices.addActionListener(e -> {Main.setCurrentPanel(() -> new ServiceTypeManagementWindow());});


		JButton butTimeSheetsCWorker = new JButton("Time Sheets");
		butTimeSheetsCWorker.addActionListener(e -> {
			Main.setCurrentPanel(() -> new TimesheetWindow(100000010));
		});

		JButton butSubmitFeedback = new JButton("Submit Feedback");
		butSubmitFeedback.addActionListener(e -> {Main.setCurrentPanel(() -> new ClientNotesView());}); // TODO: This does nothing as of now
		          
		// Add the buttons and labels to the frame

		if(args[0].toLowerCase(Locale.ROOT).equals("m")){
			buttonLayout.add(new JLabel("Manager Use Cases"));
			buttonLayout.add(butTimeSheets);
			buttonLayout.add(butServiceOrders);
			buttonLayout.add(butManageNotes);
			buttonLayout.add(butManageCaseworkers);
			buttonLayout.add(butManageServices);
		}
		if(args[0].toLowerCase(Locale.ROOT).equals("c")) {
			buttonLayout.add(new JLabel("Client Use Cases"));
			buttonLayout.add(butSubmitFeedback);
		}
		if(args[0].toLowerCase(Locale.ROOT).equals("w")) {
			buttonLayout.add(new JLabel("Case Worker Use Cases"));
			buttonLayout.add(butTimeSheetsCWorker);
		}




		this.add(buttonLayout, BorderLayout.CENTER);
	}
}
