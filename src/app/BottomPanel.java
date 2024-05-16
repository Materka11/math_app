package app;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;


public class BottomPanel extends JPanel {
	private JTextArea textArea;
	private CalculationController controller;
	
	public BottomPanel(MyWindow myWindow, JTable table) {
		CalculationModel model = new CalculationModel(table);
        CalculationView view = new CalculationView(myWindow);
        controller = new CalculationController(model, view);
        
        textArea = view.getTextArea();

        createButtomPanel(view);
	}
	
	private void createButtomPanel(CalculationView view) {
		setLayout(new BorderLayout());
	    
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));  
	    bottomPanel.add(view);
	    
	    add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public CalculationController getCalculationController() {
		return controller;
	}
}
