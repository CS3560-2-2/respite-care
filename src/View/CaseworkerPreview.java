package View;

import lib.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class CaseworkerPreview extends JPanel{

    public CaseworkerPreview(Map<String, Object> entryData, ActionListener editEvent, boolean editEnabled) {
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(10000, 40));
        //TODO: Get entry data from database
        JLabel previewLabel = new JLabel(getNameFromTimesheetEntry(entryData));
        this.add(previewLabel, BorderLayout.LINE_START);

        JButton editButton = new JButton("Edit");
        editButton.setEnabled(editEnabled);
        editButton.addActionListener(editEvent);
        this.add(editButton, BorderLayout.LINE_END);
    }
    // Write a function that given Map<String, Object>, will look up the medical number in clients table, then the ssn from the person table, and return the First and last name
    private String getNameFromTimesheetEntry(Map<String, Object> entryData) {
        // Get the medical number from the entry data
        String ssn = entryData.get("ssn").toString();
        // Get the ssn from the clients table
        Map<String, Object> personData = Connector.customQuery("SELECT * FROM Person WHERE ssn = '" + ssn + "'").get(0);
        return personData.get("firstName") + " " + personData.get("lastName");
    }
}
