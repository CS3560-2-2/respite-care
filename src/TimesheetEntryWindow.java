import lib.Connector;
import lib.MyConnector;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class TimesheetEntryWindow extends JPanel {
    JTextField textEntryDate;
    JTextField textStartTime;
    JTextField textEndTime;
    JComboBox<String> comboServiceOrder;
    JComboBox<String> comboCaregivers;

    // Called when creating a new entry
    public TimesheetEntryWindow(long employeeID) {
        this(employeeID, -1);
    }

    // Called when updating an existing entry
    public TimesheetEntryWindow(long employeeID, int entryID) {
        Map<String, Object> existingEntryData = Map.of();

        if (entryID != -1) {
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

		JPanel entryCaregiversPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labCaregivers = new JLabel("Caregiver: ");
        comboCaregivers = new JComboBox<>();
        entryCaregiversPanel.add(labCaregivers);
        entryCaregiversPanel.add(comboCaregivers);
        entryDataPanel.add(entryCaregiversPanel);

        List<Map<String, Object>> caregivers = Connector.customQuery(
                "SELECT p.ssn, p.firstName, p.lastName " +
                        "FROM Caregiver c " +
                        "JOIN Person p ON c.ssn = p.ssn"
        );
        DefaultComboBoxModel<String> caregiversModel = new DefaultComboBoxModel<>();

        // Populate the model with caregiver data
        for (Map<String, Object> caregiver : caregivers) {
            String ssn = caregiver.get("ssn").toString();
            String firstName = caregiver.get("firstName").toString();
            String lastName = caregiver.get("lastName").toString();

            String displayText = firstName + " " + lastName + " (" + ssn + ")";
            caregiversModel.addElement(displayText);
        }

        comboCaregivers.setModel(caregiversModel);

		JPanel entryServiceOrderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labServiceOrder = new JLabel("Service Order: ");
        comboServiceOrder = new JComboBox<>();
        entryServiceOrderPanel.add(labServiceOrder);
        entryServiceOrderPanel.add(comboServiceOrder);
        entryDataPanel.add(entryServiceOrderPanel);

        List<Map<String, Object>> serviceOrders = Connector.customQuery(
                "SELECT so.authNumber, s.serviceType, p.firstName, p.lastName " +
                        "FROM ServiceOrder so " +
                        "JOIN Service s ON so.serviceID = s.serviceID " +
                        "JOIN Clients c ON so.medicalNumber = c.medicalNumber " +
                        "JOIN Person p ON c.ssn = p.ssn"
        );
        DefaultComboBoxModel<String> serviceOrderModel = new DefaultComboBoxModel<>();

        // Populate the model with service order data
        for (Map<String, Object> serviceOrder : serviceOrders) {
            String authNumber = serviceOrder.get("authNumber").toString();
            String serviceType = serviceOrder.get("serviceType").toString();
            String clientFirstName = serviceOrder.get("firstName").toString();
            String clientLastName = serviceOrder.get("lastName").toString();

            String displayText = authNumber + " - " + serviceType + " - " + clientFirstName + " " + clientLastName;
            serviceOrderModel.addElement(displayText);
        }

        comboServiceOrder.setModel(serviceOrderModel);

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

        this.add(entryDataPanel, BorderLayout.CENTER);

        if (entryID != -1) {
            String[] startTime = ((LocalDateTime) existingEntryData.get("startTime")).toString().split("T");
            String[] endTime = ((LocalDateTime) existingEntryData.get("endTime")).toString().split("T");

            textEntryDate.setText(startTime[0]);
            textStartTime.setText(startTime[1]);
            textEndTime.setText(endTime[1]);

            // Find the index of the service order in the dropdown model
            int authNumber = (int) existingEntryData.get("authNumber");
            String selectedServiceOrder = null;
            for (int i = 0; i < serviceOrderModel.getSize(); i++) {
                String serviceOrder = serviceOrderModel.getElementAt(i);
                if (serviceOrder.startsWith(String.valueOf(authNumber))) {
                    selectedServiceOrder = serviceOrder;
                    break;
                }
            }

            // Set the selected service order in the dropdown
            if (selectedServiceOrder != null) {
                comboServiceOrder.setSelectedItem(selectedServiceOrder);
            }

            // Find the index of the caregiver in the dropdown model
            long caregiverSSN = (long) existingEntryData.get("ssn");
            String selectedCaregiver = null;
            for (int i = 0; i < caregiversModel.getSize(); i++) {
                String caregiver = caregiversModel.getElementAt(i);
                if (caregiver.endsWith("(" + caregiverSSN + ")")) {
                    selectedCaregiver = caregiver;
                    break;
                }
            }

            // Set the selected caregiver in the dropdown
            if (selectedCaregiver != null) {
                comboCaregivers.setSelectedItem(selectedCaregiver);
            }
        }

        // Buttons
        JButton butUpdate = new JButton("Update Entry");
        butUpdate.addActionListener(e -> submitEntryInfo(entryID, employeeID));
        JButton butDelete = new JButton("Delete Entry");
        butDelete.addActionListener(e -> deleteEntry(entryID));
        JButton butCancel = new JButton("Cancel");
        butCancel.addActionListener(e -> Main.previousPanel());
        if (entryID == -1) {
            butUpdate.setText("Add Entry");
        }

        JPanel bottomButtonsPanel = new JPanel();
        bottomButtonsPanel.add(butUpdate);
        bottomButtonsPanel.add(butDelete);
        bottomButtonsPanel.add(butCancel);

        this.add(bottomButtonsPanel, BorderLayout.AFTER_LAST_LINE);
    }

    // Gather the data from the fields and submit them
    // TODO: There's no form of error-checking here! For now we just hope the user plays nice.
    private void submitEntryInfo(int timesheetID, long employeeID) {
        String entryDate = textEntryDate.getText();
        String startTime = textStartTime.getText();
        String endTime = textEndTime.getText();
        String selectedServiceOrder = (String) comboServiceOrder.getSelectedItem();
        String[] parts = selectedServiceOrder.split(" - ");
        int serviceOrder = Integer.parseInt(parts[0]);

        String selectedCaregiver = (String) comboCaregivers.getSelectedItem();
        String[] caregiverParts = selectedCaregiver.split("\\(");
        String caregiverSSN = caregiverParts[caregiverParts.length - 1].replace(")", "");

        // Format startTime and endTime into DATETIME format
        String formattedStartTime = entryDate + " " + startTime + ":00";
        String formattedEndTime = entryDate + " " + endTime + ":00";

        Map<String, Object> updatedEntryData = new HashMap<>();
        updatedEntryData.put("startTime", formattedStartTime);
        updatedEntryData.put("endTime", formattedEndTime);
        updatedEntryData.put("authNumber", serviceOrder);
        updatedEntryData.put("ssn", caregiverSSN);

        if (timesheetID == -1) {
            // Create a new timesheet entry
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