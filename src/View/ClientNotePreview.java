package View;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ClientNotePreview extends JPanel {

    public ClientNotePreview(int noteID, Consumer<Integer> onClickEvent) {
        this(noteID, onClickEvent, false);
    }
    public ClientNotePreview(int noteID, Consumer<Integer> onClickEvent, boolean showClientName) {
        this.setLayout(new BorderLayout());

        this.add(new JLabel("<html>Note #" + noteID + "<br>Client Name: F. Last"), BorderLayout.LINE_START);
        this.add(new JLabel("<html>Three...<br>Lines of...<br>Preview text..."), BorderLayout.CENTER);

        JButton editButton = new JButton("View");
        editButton.addActionListener(e -> onClickEvent.accept(noteID));
        this.add(editButton, BorderLayout.LINE_END);
    }
}
