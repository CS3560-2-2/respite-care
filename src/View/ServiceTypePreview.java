package View;

import lib.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class ServiceTypePreview extends JPanel{

    public ServiceTypePreview(Map<String, Object> entryData, ActionListener editEvent, boolean editEnabled) {
        this.setLayout(new BorderLayout());
        this.setMaximumSize(new Dimension(10000, 40));
        for(String key : entryData.keySet()) {
            System.out.println(key + " : " + entryData.get(key));
        }
        //TODO: Get entry data from database
        JLabel previewLabel = new JLabel(
                "<html>Service #" + entryData.get("serviceID") + " " + entryData.get("serviceType") + "<br>" + entryData.get("hourlyRate") + "/hr"
        );
        this.add(previewLabel, BorderLayout.LINE_START);

        JButton editButton = new JButton("Edit");
        editButton.setEnabled(editEnabled);
        editButton.addActionListener(editEvent);
        this.add(editButton, BorderLayout.LINE_END);
    }

}
