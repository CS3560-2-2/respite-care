import lib.Connector;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class CaseworkerEditorWindow extends JPanel {

    JTextField ssnField;
    JTextField fNameField;
    JTextField lNameField;
    JTextField phNumberField;
    JTextField emailField;
    JTextField dobField;
    JTextArea certField;

    Map<String, Object> existingCaseworkerData;
    Map<String, Object> existingPersonData;

    public CaseworkerEditorWindow(long caseworkerID) {



        this.setLayout(new BorderLayout());

        JPanel topInfoLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topInfoLayout.add(new JLabel("Create or Edit Caseworker"));
        JButton butAddEntry = new JButton("Save");
        butAddEntry.addActionListener(e -> {addEntry();});
        JButton butBack = new JButton("Back");
        butBack.addActionListener(e -> {Main.previousPanel();});
        topInfoLayout.add(butAddEntry);
        topInfoLayout.add(butBack);
        this.add(topInfoLayout, BorderLayout.BEFORE_FIRST_LINE);

        JPanel formContent = new JPanel();
        formContent.setLayout(new BoxLayout(formContent, BoxLayout.PAGE_AXIS));

        ssnField = new JTextField(12);
        formContent.add(newFormRow("SSN: ", ssnField));

        fNameField = new JTextField(30);
        formContent.add(newFormRow("First Name: ", fNameField));

        lNameField = new JTextField(30);
        formContent.add(newFormRow("Last Name: ", lNameField));

        phNumberField = new JTextField(20);
        formContent.add(newFormRow("Phone Number: ", phNumberField));

        emailField = new JTextField(50);
        formContent.add(newFormRow("Email: ", emailField));

        dobField = new JTextField("yyyy-mm-dd",16);
        formContent.add(newFormRow("Date of Birth: ", dobField));

        certField = new JTextArea();
        certField.setLineWrap(true);
        certField.setEditable(true);
        certField.setColumns(25);
        certField.setRows(4);

        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newPanel.add(new JLabel("Certification: "));
        newPanel.add(certField);

        formContent.add(newPanel);

        if(caseworkerID != -1) {
            existingPersonData = Connector.getMatchingRows(
                    "Person", Map.of("ssn", caseworkerID)
            ).get(0);
            existingCaseworkerData = Connector.getMatchingRows(
                    "Caregiver", Map.of("ssn", caseworkerID)
            ).get(0);

            ssnField.setText(existingPersonData.get("ssn").toString());
            fNameField.setText(existingPersonData.get("firstName").toString());
            lNameField.setText(existingPersonData.get("lastName").toString());
            phNumberField.setText(existingPersonData.get("phoneNumber").toString());
            emailField.setText(existingPersonData.get("emailAddress").toString());
            dobField.setText(existingPersonData.get("dateOfBirth").toString());
            certField.setText(existingCaseworkerData.get("certification").toString());

        }

        this.add(formContent, BorderLayout.CENTER);
    }

    private void addEntry() {
        if (!validateForm()) return;
        Map<String, Object> newPersonData = compilePersonFormData();
        Map<String, Object> newCaseworkerData = compileCaseworkerFormData();

        if(existingPersonData != null) {
            Connector.updateRow("Person", existingPersonData, newPersonData);
        } else {
            Connector.insertRow("Person", newPersonData);
        }

        if(existingCaseworkerData != null) {
            Connector.updateRow("Caregiver", existingCaseworkerData, newCaseworkerData);
        } else {
            Connector.insertRow("Caregiver", newCaseworkerData);
        }

        Main.previousPanel();
    }

    // Simple form validation
    private boolean validateForm() {
        return (!ssnField.getText().isEmpty()
                && !fNameField.getText().isEmpty()
                && !lNameField.getText().isEmpty()
                && !phNumberField.getText().isEmpty()
                && !emailField.getText().isEmpty()
                && !dobField.getText().isEmpty());
    }

    private Map<String, Object> compilePersonFormData() {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        try {
            java.sql.Date sqlDate = new java.sql.Date(df.parse(dobField.getText()).getTime());
            return Map.of(
                    "ssn", Long.parseLong(ssnField.getText()),
                    "firstName", fNameField.getText(),
                    "lastName", lNameField.getText(),
                    "phoneNumber", phNumberField.getText(),
                    "emailAddress", emailField.getText(),
                    "dateOfBirth", sqlDate
                    );
        } catch (ParseException e) {
            return Map.of();
        }
    }

    private Map<String, Object> compileCaseworkerFormData() {
        return Map.of(
                "ssn", Long.parseLong(ssnField.getText()),
                "certification", certField.getText()
        );
    }
    private JPanel newFormRow(String paramName, JTextField field) {
        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newPanel.add(new JLabel(paramName));
        newPanel.add(field);
        return newPanel;
    }
}
