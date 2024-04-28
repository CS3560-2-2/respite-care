import javax.swing.*;
import java.awt.*;

public class ClientNoteViewer extends JPanel {
    public ClientNoteViewer(int noteID) {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        topPanel.add(new JLabel("Note #" + noteID));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {Main.previousPanel();});
        topPanel.add(backButton);

        JTextArea text = new JTextArea("START sdaljgkadwjglkasdgl adshglkadslgkjadsjkg ladghlkajsdg lasdg lasjkdg laksjldkjglasjdg sadjklgadsghlaskjdglasjd asdglkjas ldkjglasdg lkj FULL STOP");
        text.setEditable(false);
        text.setLineWrap(true);
        this.add(text, BorderLayout.CENTER);
    }
}
