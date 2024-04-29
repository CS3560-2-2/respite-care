import lib.Connector;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class EditServiceOrderWindow extends JPanel {
    private JTextField authNumberField;
    private JComboBox<String> clientDropdown;
    private JComboBox<String> serviceDropdown;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField totalHoursField;

    public EditServiceOrderWindow(int orderID) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        authNumberField = new JTextField();
        clientDropdown = new JComboBox<>();
        serviceDropdown = new JComboBox<>();
        startDateField = new JTextField();
        endDateField = new JTextField();
        totalHoursField = new JTextField();

        // Populate client dropdown
        List<Map<String, Object>> clients = Connector.getList("Clients");
        for (Map<String, Object> client : clients) {
            String medicalNumber = client.get("medicalNumber").toString();
            String ssn = client.get("ssn").toString();

            // Get client name from Person table
            List<Map<String, Object>> personList = Connector.customQuery("SELECT firstName, lastName FROM Person WHERE ssn = ?", List.of(ssn));
            if (!personList.isEmpty()) {
                Map<String, Object> person = personList.get(0);
                String clientName = person.get("firstName") + " " + person.get("lastName");
                clientDropdown.addItem(clientName + " (" + medicalNumber + ")");
            }
        }

        // Populate service dropdown
        List<Map<String, Object>> services = Connector.getList("Service");
        for (Map<String, Object> service : services) {
            String serviceId = service.get("serviceID").toString();
            String serviceType = service.get("serviceType").toString();
            serviceDropdown.addItem(serviceType + " (" + serviceId + ")");
        }

        // Populate fields if editing existing order
        if (orderID != -1) {
            List<Map<String, Object>> serviceOrders = Connector.customQuery("SELECT * FROM ServiceOrder WHERE authNumber = ?", List.of(orderID));
            if (!serviceOrders.isEmpty()) {
                Map<String, Object> serviceOrder = serviceOrders.get(0);
                authNumberField.setText(serviceOrder.get("authNumber").toString());
                startDateField.setText(serviceOrder.get("startDate").toString());
                endDateField.setText(serviceOrder.get("endDate").toString());
                totalHoursField.setText(serviceOrder.get("totalHoursAllowed").toString());

                // Set selected client
                String medicalNumber = serviceOrder.get("medicalNumber").toString();
                for (int i = 0; i < clientDropdown.getItemCount(); i++) {
                    if (clientDropdown.getItemAt(i).contains(medicalNumber)) {
                        clientDropdown.setSelectedIndex(i);
                        break;
                    }
                }

                // Set selected service
                String serviceId = serviceOrder.get("serviceID").toString();
                for (int i = 0; i < serviceDropdown.getItemCount(); i++) {
                    if (serviceDropdown.getItemAt(i).contains(serviceId)) {
                        serviceDropdown.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }

        this.add(new JLabel("Auth Number:"));
        this.add(authNumberField);
        this.add(new JLabel("Client:"));
        this.add(clientDropdown); 
        this.add(new JLabel("Service:"));
        this.add(serviceDropdown);
        this.add(new JLabel("Start Date (YYYY-MM-DD):"));
        this.add(startDateField);
        this.add(new JLabel("End Date (YYYY-MM-DD):"));  
        this.add(endDateField);
        this.add(new JLabel("Total Hours:"));
        this.add(totalHoursField);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton saveButton = new JButton("Update Entry");
        saveButton.addActionListener(e -> saveServiceOrder(orderID));
        buttonPanel.add(saveButton);

        JButton deleteButton = new JButton("Delete Entry");
        deleteButton.addActionListener(e -> deleteServiceOrder(orderID));
        buttonPanel.add(deleteButton);

        JButton cancelButton = new JButton("Exit without changes");
        cancelButton.addActionListener(e -> Main.previousPanel());
        buttonPanel.add(cancelButton);

        // Add the button panel to the main panel
        this.add(buttonPanel);
    }

    private void saveServiceOrder(int orderID) {
        String authNumber = authNumberField.getText();
        String client = (String) clientDropdown.getSelectedItem();
        String medicalNumber = client.substring(client.lastIndexOf("(") + 1, client.lastIndexOf(")"));
        String service = (String) serviceDropdown.getSelectedItem();
        String serviceId = service.substring(service.lastIndexOf("(") + 1, service.lastIndexOf(")"));
        String startDate = startDateField.getText();  
        String endDate = endDateField.getText();
        String totalHours = totalHoursField.getText();

        if (orderID == -1) {
            // Create new service order
            Map<String, Object> serviceOrder = Map.of(
                "authNumber", authNumber,
                "medicalNumber", medicalNumber, 
                "serviceID", serviceId,
                "startDate", startDate,
                "endDate", endDate,
                "totalHoursAllowed", totalHours
            );
            Connector.insertRow("ServiceOrder", serviceOrder);
        } else {
            // Update existing service order  
            Map<String, Object> oldServiceOrder = Map.of("authNumber", orderID);
            Map<String, Object> newServiceOrder = Map.of( 
                "medicalNumber", medicalNumber,
                "serviceID", serviceId,  
                "startDate", startDate,
                "endDate", endDate,
                "totalHoursAllowed", totalHours
            );
            Connector.updateRow("ServiceOrder", oldServiceOrder, newServiceOrder);
        }

        Main.previousPanel();
    }
    private void deleteServiceOrder(int orderID) {
        if (orderID != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this service order?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Map<String, Object> serviceOrder = Map.of("authNumber", orderID);
                Connector.deleteRow("ServiceOrder", serviceOrder);
                Main.previousPanel();
            }
        }
    }
}