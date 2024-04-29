package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.function.Consumer;

public class TimesheetEntryPreview extends JPanel {

    public TimesheetEntryPreview(Map<String, Object> entryData, Consumer<ActionEvent> editEvent, boolean editEnabled) {
        this.setLayout(new BorderLayout());

        //TODO: Get entry data from database
        JLabel previewLabel = new JLabel("<html>Preview information for timesheet entry #" + entryData.get("timesheetID")
                + "<br>" + entryData.get("startTime") + " - " + entryData.get("endTime"));
        this.add(previewLabel, BorderLayout.LINE_START);

        JButton editButton = new JButton("Edit");
        editButton.setEnabled(editEnabled);
        // TODO: call `TimesheetEntryWindow(String employeeName, int entryID)` when clicked
        this.add(editButton, BorderLayout.LINE_END);
    }
}
