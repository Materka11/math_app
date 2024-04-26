package app;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

public class StatusPanel extends JPanel {
	
	private static JLabel eventLabel;
	private static JTextField numRow, numCol;
	
	public StatusPanel() {
		setLayout(new BorderLayout());
		initGUI();
		
		add(createCenterPanel(), BorderLayout.CENTER);
	}
	
	private void initGUI() {
		eventLabel = new JLabel("Stat aplikacji", JLabel.LEFT);
		numRow = new JTextField("1");
		numRow.setHorizontalAlignment(JTextField.RIGHT);
		numCol = new JTextField("1");
		numCol.setHorizontalAlignment(JTextField.RIGHT);
	}
	
	private JPanel createCenterPanel() {
	    JPanel jp = new JPanel();
	    jp.setBackground(Color.LIGHT_GRAY);
	    FormLayout formLayout = new FormLayout(
	            "2dlu, pref:grow, 20dlu, 25dlu, 3dlu, 25dlu, 4dlu", 
	            "3dlu, pref"); 
	    jp.setLayout(formLayout);
	    CellConstraints cc = new CellConstraints();
	    jp.add(new JSeparator(JSeparator.HORIZONTAL),
	            cc.xyw(1, 1, 7)); 
	    
	    jp.add(eventLabel, cc.xy(2, 2));
	    jp.add(numRow, cc.xy(4, 2));
	    jp.add(numCol, cc.xy(6, 2)); 
	    
	    return jp;
	}
}
