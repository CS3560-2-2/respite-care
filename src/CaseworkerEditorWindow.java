import javax.swing.*;
import java.awt.*;

public class CaseworkerEditorWindow extends JPanel {

    JTextField ssnField;
    JTextField fNameField;
    JTextField lNameField;
    JTextField phNumberField;
    JTextField emailField;
    JTextField dobField;
    JTextArea certField;

    public CaseworkerEditorWindow(long caseworkerID) {
        this.setLayout(new BorderLayout());

        JPanel topInfoLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topInfoLayout.add(new JLabel("Create or Edit Caseworker"));
        JButton butAddEntry = new JButton("Save");
        butAddEntry.addActionListener(e -> {});
        JButton butBack = new JButton("Back");
        butBack.addActionListener(e -> {Main.previousPanel();});
        topInfoLayout.add(butAddEntry);
        topInfoLayout.add(butBack);
        this.add(topInfoLayout, BorderLayout.BEFORE_FIRST_LINE);

        JPanel formContent = new JPanel();
        formContent.setLayout(new BoxLayout(formContent, BoxLayout.PAGE_AXIS));

        ssnField = new JTextField(12);
        formContent.add(newFormRow("SSN: ", ssnField));

        fNameField = new JTextField(30);
        formContent.add(newFormRow("First Name: ", fNameField));

        lNameField = new JTextField(30);
        formContent.add(newFormRow("Last Name: ", lNameField));

        phNumberField = new JTextField(20);
        formContent.add(newFormRow("Phone Number: ", phNumberField));

        emailField = new JTextField(50);
        formContent.add(newFormRow("Email: ", emailField));

        dobField = new JTextField("yyyy-mm-dd",16);
        formContent.add(newFormRow("Date of Birth: ", dobField));

        certField = new JTextArea();
        certField.setLineWrap(true);
        certField.setEditable(true);
        certField.setColumns(25);
        certField.setRows(4);

        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newPanel.add(new JLabel("Certification: "));
        newPanel.add(certField);

        formContent.add(newPanel);

        this.add(formContent, BorderLayout.CENTER);
    }

    private JPanel newFormRow(String paramName, JTextField field) {
        JPanel newPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newPanel.add(new JLabel(paramName));
        newPanel.add(field);
        return newPanel;
    }
}
