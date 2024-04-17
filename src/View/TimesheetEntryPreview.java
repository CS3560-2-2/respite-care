package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class TimesheetEntryPreview extends JPanel {

    public TimesheetEntryPreview(int entryID, Consumer<ActionEvent> editEvent, boolean editEnabled) {
        this.setLayout(new BorderLayout());

        //TODO: Get entry data from database
        JLabel previewLabel = new JLabel("Preview information for timesheet entry #" + entryID);
        this.add(previewLabel, BorderLayout.LINE_START);

        JButton editButton = new JButton("Edit");
        editButton.setEnabled(editEnabled);
        this.add(editButton, BorderLayout.LINE_END);
    }
}
