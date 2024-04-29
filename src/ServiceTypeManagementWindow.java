import View.CaseworkerPreview;
import View.ServiceTypePreview;
import lib.Connector;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ServiceTypeManagementWindow extends JPanel {
    JPanel pageContentPanel;

    public ServiceTypeManagementWindow() {
        this.setLayout(new BorderLayout());

        JPanel topInfoLayout = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topInfoLayout.add(new JLabel("Manage Services"));

        JButton butAddEntry = new JButton("+ New Service");
        butAddEntry.addActionListener(e -> {
            Main.setCurrentPanel(() -> new ServiceTypeEditorWindow(-1));
        });
        JButton butBack = new JButton("Back");
        butBack.addActionListener(e -> {Main.previousPanel();});
        topInfoLayout.add(butAddEntry);
        topInfoLayout.add(butBack);


        pageContentPanel = new JPanel();
        pageContentPanel.setLayout(new BoxLayout(pageContentPanel, BoxLayout.PAGE_AXIS));


        /*java.util.List<Map<String, Object>> services = Connector.getList("Service");
        for(Map<String, Object> service : services) {
            pageContentPanel.add(new ServiceTypePreview(
                    service,
                    e -> {},
                    true
            ));
        }*/


        this.add(topInfoLayout, BorderLayout.BEFORE_FIRST_LINE);
        this.add(new JScrollPane(pageContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pageContentPanel.removeAll();
        java.util.List<Map<String, Object>> services = Connector.getList("Service");
        for(Map<String, Object> service : services) {
            pageContentPanel.add(new ServiceTypePreview(
                    service,
                    e -> {Main.setCurrentPanel(() -> new ServiceTypeEditorWindow((int)service.get("serviceID")));},
                    true
            ));
        }
    }
}
