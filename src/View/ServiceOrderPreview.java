package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ServiceOrderPreview extends JPanel {
    public ServiceOrderPreview(int orderID, Consumer<Integer> onEditEvent) {
        this.setLayout(new BorderLayout());

        this.add(new JLabel("<html>Service Order #" + orderID + "<br>Client Name: F. Last<br>Expires: mm/dd/yyyy"), BorderLayout.LINE_START);

        JButton editButton = new JButton("Edit Order");
        editButton.addActionListener(e -> onEditEvent.accept(orderID));
        this.add(editButton, BorderLayout.LINE_END);

        //this.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        //this.validate();
    }
}
