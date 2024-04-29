package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;
import lib.Connector;

public class ServiceOrderPreview extends JPanel {
    public ServiceOrderPreview(Map<String, Object> serviceOrder, Consumer<Integer> onEditEvent) {
        this.setLayout(new BorderLayout());

        int orderID = (int) serviceOrder.get("authNumber");
        String clientSSN = serviceOrder.get("medicalNumber").toString();
        String expiryDate = serviceOrder.get("endDate").toString();

        // Get the client name using the SSN
        Map<String, Object> filters = new HashMap<>();
        filters.put("medicalNumber", clientSSN);
        List<Map<String, Object>> clientList = Connector.getMatchingRows("Clients", filters);

        String clientName = "";
        if (!clientList.isEmpty()) {
            Map<String, Object> client = clientList.get(0);
            String personSSN = client.get("ssn").toString();
            filters.clear();
            filters.put("ssn", personSSN);
            List<Map<String, Object>> personList = Connector.getMatchingRows("Person", filters);
            if (!personList.isEmpty()) {
                Map<String, Object> person = personList.get(0);
                clientName = person.get("firstName") + " " + person.get("lastName");
            }
        }

        this.add(new JLabel("<html>Service Order #" + orderID + "<br>Client Name: " + clientName + "<br>Expires: " + expiryDate), BorderLayout.LINE_START);

        JButton editButton = new JButton("Edit Order");
        editButton.addActionListener(e -> onEditEvent.accept(orderID));
        this.add(editButton, BorderLayout.LINE_END);
    }
}