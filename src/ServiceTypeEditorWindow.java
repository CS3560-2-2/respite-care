import lib.Connector;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ServiceTypeEditorWindow extends JPanel {

    JTextField idField;
    JTextField serviceName;
    JTextField priceField;

    Map<String, Object> existingSeviceData;

    public ServiceTypeEditorWindow(long serviceID) {



        this.setLayout(new BorderLayout());

        JPanel topInfoLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topInfoLayout.add(new JLabel("Create or Edit Service Type"));
        JButton butAddEntry = new JButton("Save");
        butAddEntry.addActionListener(e -> {addEntry();});
        JButton butBack = new JButton("Back");
        butBack.addActionListener(e -> {Main.previousPanel();});
        topInfoLayout.add(butAddEntry);
        topInfoLayout.add(butBack);
        this.add(topInfoLayout, BorderLayout.BEFORE_FIRST_LINE);

        JPanel formContent = new JPanel();
        formContent.setLayout(new BoxLayout(formContent, BoxLayout.PAGE_AXIS));

        idField = new JTextField(10);
        formContent.add(newFormRow("Service Type ID: ", idField));

        serviceName = new JTextField(30);
        formContent.add(newFormRow("Service Type Name: ", serviceName));

        priceField = new JTextField(30);
        formContent.add(newFormRow("Price/hr: ", priceField));

        if(serviceID != -1) {
            existingSeviceData = Connector.getMatchingRows(
                    "Service", Map.of("serviceID", serviceID)
            ).get(0);

            idField.setText(existingSeviceData.get("serviceID").toString());
            serviceName.setText(existingSeviceData.get("serviceType").toString());
            priceField.setText(existingSeviceData.get("hourlyRate").toString());

            JButton delButton = new JButton("Delete Service");
            delButton.addActionListener(e -> {
                Connector.deleteRow("Service", existingSeviceData);
                Main.previousPanel();
            });
            topInfoLayout.add(delButton);
        }

        this.add(formContent, BorderLayout.CENTER);
    }

    private void addEntry() {
        if (!validateForm()) return;
        Map<String, Object> newServiceData = compileServiceFormData();

        if(existingSeviceData != null) {
            Connector.updateRow("Service", existingSeviceData, newServiceData);
        } else {
            Connector.insertRow("Service", newServiceData);
        }

        Main.previousPanel();
    }

    // Simple form validation
    private boolean validateForm() {
        return (!idField.getText().isEmpty()
                && !serviceName.getText().isEmpty()
                && !priceField.getText().isEmpty());
    }

    private Map<String, Object> compileServiceFormData() {
        return Map.of(
                "serviceID", Integer.parseInt(idField.getText()),
                "serviceType", serviceName.getText(),
                "hourlyRate", Float.parseFloat(priceField.getText())
                );
    }
    private JPanel newFormRow(String paramName, JTextField field) {
        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newPanel.add(new JLabel(paramName));
        newPanel.add(field);
        newPanel.setMaximumSize(new Dimension(10000, 40));
        return newPanel;
    }
}
