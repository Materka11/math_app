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
	
	private CenterPanel centerPanel;
	
	public StatusPanel(CenterPanel centerPanel) {
		this.centerPanel = centerPanel;
		
		setLayout(new BorderLayout());
		initGUI();
		
		add(createCenterPanel(), BorderLayout.CENTER);
		
		centerPanel.getLabelPanel().getRowSlider().addChangeListener(e -> {
	       numRow.setText(Integer.toString(centerPanel.getLabelPanel().getRowSlider().getValue()));
	     });
	        
	    centerPanel.getLabelPanel().getColSlider().addChangeListener(e -> {
	       numCol.setText(Integer.toString(centerPanel.getLabelPanel().getColSlider().getValue()));
	    });
	}
	
	private void initGUI() {
		eventLabel = new JLabel("Status aplikacji", JLabel.LEFT);
		
		String rowValue = Integer.toString(centerPanel.getLabelPanel().getRowSlider().getValue());
	    String colValue = Integer.toString(centerPanel.getLabelPanel().getColSlider().getValue());
	    
		numRow = new JTextField(rowValue);
		numRow.setHorizontalAlignment(JTextField.RIGHT);
		numRow.setEditable(false);
		numCol = new JTextField(colValue);
		numCol.setHorizontalAlignment(JTextField.RIGHT);
		numCol.setEditable(false);
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
