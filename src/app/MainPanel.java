package app;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private CenterPanel centerPanel;
	private BottomPanel bottomPanel;
	
	private MyWindow myWindow;
	
	public MainPanel(MyWindow myWindow) {
		this.myWindow = myWindow;
		
		centerPanel = new CenterPanel(myWindow);	
		bottomPanel = new BottomPanel(myWindow);
		
		createMainPanel(centerPanel, bottomPanel);
	}
	
	private void createMainPanel(JPanel centerPanel, JPanel bottomPanel) {
	    JPanel mainPanel = new JPanel(new BorderLayout());
	    mainPanel.add(centerPanel, BorderLayout.CENTER);
	    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	    
	    add(mainPanel, BorderLayout.CENTER);
	}
}
