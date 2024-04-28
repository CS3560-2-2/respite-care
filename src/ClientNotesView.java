import javax.swing.*;
import java.awt.*;

public class ClientNotesView extends JPanel {

    public ClientNotesView() {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        topPanel.add(new JLabel("My Notes"));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {Main.previousPanel();});
        topPanel.add(backButton);

        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.PAGE_AXIS));

        //TODO: Code to add notes preview here

        this.add(new JScrollPane(notesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

        JPanel bottomPannel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton newEntryButton = new JButton("Create New Note");
        newEntryButton.addActionListener(e -> {});
        bottomPannel.add(newEntryButton);
        this.add(bottomPannel, BorderLayout.AFTER_LAST_LINE);


    }

}
