import View.ServiceOrderPreview;

import javax.swing.*;
import java.awt.*;

public class ServiceOrderWindow extends JPanel {

    static int[] serviceOrders = {123456789, 158159,138597138, 3589725, 123456789, 158159,138597138, 3589725, 123456789, 158159};

    public ServiceOrderWindow() {
        this.setLayout(new BorderLayout());

        JPanel topRowPanel = new JPanel();

        JLabel serviceOrderViewLabel = new JLabel("Viewing Open Service Orders");
        JButton newOrderButton = new JButton("New Order");
        newOrderButton.addActionListener(e -> createServiceOrder());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {Main.previousPanel();});
        topRowPanel.add(serviceOrderViewLabel);
        topRowPanel.add(newOrderButton);
        topRowPanel.add(backButton);

        this.add(topRowPanel, BorderLayout.BEFORE_FIRST_LINE);

        JPanel serviceOrderPane = new JPanel();
        serviceOrderPane.setLayout(new BoxLayout(serviceOrderPane, BoxLayout.PAGE_AXIS));

        for(int i = 0; i < serviceOrders.length; i++) {
            int finalI = i;
            ServiceOrderPreview preview = new ServiceOrderPreview(
                    serviceOrders[i],
                    id -> viewServiceOrder(id)
            );
            serviceOrderPane.add(preview);
        }

        this.add(new JScrollPane(serviceOrderPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public static void createServiceOrder() {
        Main.setCurrentPanel(new EditServiceOrderWindow(-1));
    }

    public static void viewServiceOrder(int orderID) {
        Main.setCurrentPanel(new EditServiceOrderWindow(orderID));
    }
}
