import javax.swing.*;
import java.awt.*;

public class EditServiceOrderWindow extends JPanel {

    JTextField clientNameField;
    JTextField startDateField;
    JTextField endDateField;
    JComboBox employeeField;
    JTextField maxHoursField;
    JTextField billingRateField;
    JTextArea notesField;

    public EditServiceOrderWindow(int orderID) {
        this.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        if(orderID != -1) {
            topPanel.add(new JLabel("Viewing Service Order #" + orderID), BorderLayout.BEFORE_FIRST_LINE);
            JButton updateButton = new JButton("Update Order");
            updateButton.addActionListener(e -> saveDataToOrder(orderID));
            topPanel.add(updateButton);
        } else {
            topPanel.add(new JLabel("Creating new Service Order"), BorderLayout.BEFORE_FIRST_LINE);
            JButton updateButton = new JButton("Save Order");
            updateButton.addActionListener(e -> createNewOrder());
            topPanel.add(updateButton);
        }
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {Main.previousPanel();});
        topPanel.add(backButton);

        this.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);



        JPanel infoLayout = new JPanel();
        infoLayout.setLayout(new BoxLayout(infoLayout, BoxLayout.PAGE_AXIS));

        JPanel clientNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clientNamePanel.add(new JLabel("Client Name:"));
        clientNameField = new JTextField();
        clientNameField.setColumns(40);
        clientNamePanel.add(clientNameField);
        infoLayout.add(clientNamePanel);

        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDatePanel.add(new JLabel("Start Date:"));
        startDateField = new JTextField();
        startDateField.setColumns(12);
        startDatePanel.add(startDateField);
        infoLayout.add(startDatePanel);

        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        endDatePanel.add(new JLabel("End Date:"));
        endDateField = new JTextField();
        endDateField.setColumns(12);
        endDatePanel.add(endDateField);
        infoLayout.add(endDatePanel);


        //Todo: Get name of employees for field
        JPanel employeePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        employeePanel.add(new JLabel("Employee Name:"));
        employeeField = new JComboBox();
        employeePanel.add(employeeField);
        infoLayout.add(employeePanel);


        JPanel maxHoursPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maxHoursPanel.add(new JLabel("Max Hours:"));
        maxHoursField = new JTextField();
        maxHoursField.setColumns(12);
        maxHoursPanel.add(maxHoursField);
        infoLayout.add(maxHoursPanel);

        JPanel billingRatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        billingRatePanel.add(new JLabel("Billing Rate:"));
        billingRateField = new JTextField();
        billingRateField.setColumns(12);
        billingRatePanel.add(billingRateField);
        infoLayout.add(billingRatePanel);

        JPanel entryNotesPanel = new JPanel(new BorderLayout());
        JLabel labNotes = new JLabel("Notes: ");
        labNotes.setHorizontalAlignment(JLabel.LEFT);
        notesField = new JTextArea(4, 50);
        notesField.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(notesField);
        entryNotesPanel.add(labNotes, BorderLayout.BEFORE_FIRST_LINE);
        entryNotesPanel.add(scrollPane, BorderLayout.CENTER);
        infoLayout.add(entryNotesPanel);

        this.add(infoLayout, BorderLayout.CENTER);



    }

    static void fillFormWithOrderInfo(int id) {
        //TODO: Fill the forms fields with data from an existing order
    }

    static void saveDataToOrder(int id) {
        //TODO: Compile data and use to overwrite an existing order
    }

    static void createNewOrder() {
        //TODO: Compile data from form and create a new database order
    }
}
