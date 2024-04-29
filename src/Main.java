import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import lib.Connector;

public class Main {
	// The main JFrame for the application
	public static JFrame frame;
	public static JPanel currentPanel;
	public static Stack<JPanelBuilder> panelStack;

	public static void main(String[] args) {
		Connector.testDictionaryGet();
		frame = new JFrame("Respite Management Portal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension minDim = new Dimension();
		minDim.setSize(600, 400);
		frame.setMinimumSize(minDim);
		panelStack = new Stack<JPanelBuilder>();
		// Start up the GUI
		setCurrentPanel(() -> new PortalWindow(args));

		frame.setVisible(true);
	}
	
	// Switch to a new panel 
	public static void setCurrentPanel(JPanelBuilder panel) {
		// Remove the current panel (if any)
		if (panelStack.size() > 0)
		{
			frame.remove(currentPanel);
		}
		currentPanel = panel.run();
		panelStack.push(panel);
		frame.add(currentPanel);
		frame.validate();
		frame.repaint();
	}
	
	// Go back to the previous panel
	// FIXME: When this method is called, the elements of the removed panel linger don't 
	// seem to be removed until the user messes with the window size settings. There's 
	// probably a simple cause/solution here that I'm missing...
	public static void previousPanel() {
		// If we have multiple panels, remove the topmost one
		if(panelStack.size() > 1) {
			frame.remove(currentPanel);
			panelStack.pop();
		}
		// Switch to the panel directly before it
		currentPanel = panelStack.peek().run();
		frame.add(currentPanel);
		frame.validate();
		frame.repaint();
		frame.setVisible(true);
	}
}