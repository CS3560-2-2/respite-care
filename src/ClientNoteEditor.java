import javax.swing.*;
import java.awt.*;

public class ClientNoteEditor extends JPanel {
    public ClientNoteEditor(int clientID) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        topPanel.add(new JLabel("Create a new note"));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {Main.previousPanel();});
        topPanel.add(backButton);

        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        this.add(new JScrollPane(text), BorderLayout.CENTER);

        JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {submitClientNote(clientID, text);});
        bottomRow.add(submit);
        this.add(bottomRow, BorderLayout.AFTER_LAST_LINE);
    }

    static void submitClientNote(int clientID, JTextArea textarea) {
        //TODO: Get data and use to create new note in database
        Main.previousPanel();
    }
}
