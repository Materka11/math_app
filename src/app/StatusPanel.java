package app;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class StatusPanel extends JPanel{
	
	private static JLabel eventLabel;
	private static JTextField numRow, numCol;
	
	public StatusPanel() {
		setLayout(new BorderLayout());
		initGUI();
//		add(createCenterPanel(), BorderLayout.CENTER);
	}
	
	private void initGUI() {
		eventLabel = new JLabel("Stat aplikacji", JLabel.LEFT);
		numRow = new JTextField("1");
		numRow.setHorizontalAlignment(JTextField.RIGHT);
		numCol = new JTextField("1");
		numCol.setHorizontalAlignment(JTextField.RIGHT);
	}
	//lib -> forms-1.2.0
	
//	private JPanel createCenterPanel() {
//		JPanel jp = new JPanel();
//		jp.setBackground(Color.LIGHT_GRAY);
//		FormLayout formLayout = new FormLayout(
//				"2dlu", "pref:grow", "20dlu", "25dlu", "3dlu", "25dlu", "4dlu",
//				"pref", "1dlu", "pref", "2dlu");
//		jp.setLayout(formLayout);
//		CellConstraints cc = new CellContraints();
//		jp.add(new JSeparator(JSeparator.HORIZONTAL),
//				cc.xyw(1,1,7,CellConstraints.FILL, CellConstraints.CENTER));
//		jp.add(eventLabel, cc.xy(2,3,CellConstraints.FILL,CellConstraints.FILL));
//		jp.add(numRow, cc.xy(4,3,CellConstraints.FILL,CellConstraints.FILL));
//		jp.add(numCol, cc.xy(6,3,CellContraints.FILL,CellContraints.FILL));
//		
//		return jp;
//		
//	}
}
