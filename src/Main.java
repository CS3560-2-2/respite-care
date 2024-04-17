import javax.swing.*;
import java.awt.*;

public class Main {

	public static JFrame frame;
	public static JPanel currentPanel;

	public static void main(String[] args) {
		frame = new JFrame("Respite Management Portal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension minDim = new Dimension();
		minDim.setSize(600, 400);
		frame.setMinimumSize(minDim);
		// Start up the GUI
		setCurrentPanel(new PortalWindow());

		frame.setVisible(true);
	}

	public static void setCurrentPanel(JPanel panel) {
		if(currentPanel != null) {
			frame.remove(currentPanel);
		}
		currentPanel = panel;
		frame.add(currentPanel);
		frame.validate();
	}
}
