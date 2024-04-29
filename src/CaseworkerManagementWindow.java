import View.CaseworkerPreview;
import lib.Connector;
import lib.MyConnector;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.List;

public class CaseworkerManagementWindow extends JPanel {

    JPanel pageContentPanel;
    public CaseworkerManagementWindow() {
        this.setLayout(new BorderLayout());

        JPanel topInfoLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topInfoLayout.add(new JLabel("Manage Caseworkers"));

        JButton butAddEntry = new JButton("+ New Caseworker");
        butAddEntry.addActionListener(e -> {
            Main.setCurrentPanel(() -> new CaseworkerEditorWindow(-1));
        });
        JButton butBack = new JButton("Back");
        butBack.addActionListener(e -> {Main.previousPanel();});
        topInfoLayout.add(butAddEntry);
        topInfoLayout.add(butBack);


        pageContentPanel = new JPanel();
        pageContentPanel.setLayout(new BoxLayout(pageContentPanel, BoxLayout.PAGE_AXIS));


        List<Map<String, Object>> caregivers = Connector.getList("Caregiver");

        for(Map<String, Object> caregiver : caregivers) {
            pageContentPanel.add(new CaseworkerPreview(
                    caregiver,
                    e -> {
                        Main.setCurrentPanel(() -> new CaseworkerEditorWindow((long)caregiver.get("ssn")));
                    },
                    true
            ));
        }


        this.add(topInfoLayout, BorderLayout.BEFORE_FIRST_LINE);
        this.add(new JScrollPane(pageContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pageContentPanel.removeAll();
        List<Map<String, Object>> caregivers = Connector.getList("Caregiver");

        for(Map<String, Object> caregiver : caregivers) {
            pageContentPanel.add(new CaseworkerPreview(
                    caregiver,
                    e -> {
                        Main.setCurrentPanel(() -> new CaseworkerEditorWindow((long)caregiver.get("ssn")));
                    },
                    true
            ));
        }
    }
}
