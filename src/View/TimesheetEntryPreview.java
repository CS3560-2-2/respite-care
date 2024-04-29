package View;

import javax.swing.*;

import lib.Connector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Map;
import java.util.function.Consumer;

public class TimesheetEntryPreview extends JPanel {

    public TimesheetEntryPreview(Map<String, Object> entryData, ActionListener editEvent, boolean editEnabled) {
        this.setLayout(new BorderLayout());
        Connector.printListMap(entryData);
        //TODO: Get entry data from database
        JLabel previewLabel = new JLabel("<html>Name: " + getNameFromTimesheetEntry(entryData) + 
            " Timesheet ID: " + entryData.get("timesheetID") + "<br>" + entryData.get("startTime") + 
            " - " + entryData.get("endTime"));
        this.add(previewLabel, BorderLayout.LINE_START);

        JButton editButton = new JButton("Edit");
        editButton.setEnabled(editEnabled);
        editButton.addActionListener(editEvent);
        // TODO: call `TimesheetEntryWindow(String employeeName, int entryID)` when clicked
        this.add(editButton, BorderLayout.LINE_END);
    }
    // Write a function that given Map<String, Object>, will look up the medical number in clients table, then the ssn from the person table, and return the First and last name
private String getNameFromTimesheetEntry(Map<String, Object> entryData) {
    // Get the medical number from the entry data
    String ssn = entryData.get("ssn").toString();
    // Get the ssn from the clients table
    Map<String, Object> personData = Connector.customQuery("SELECT * FROM Person WHERE ssn = '" + ssn + "'").get(0);
    System.out.println(personData);
    return personData.get("firstName") + " " + personData.get("lastName");
    }
}