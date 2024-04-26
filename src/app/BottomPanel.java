package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class BottomPanel extends JPanel {
	private static final String[] CALCULATIONS = {"Suma","Średnia","Minimum","Maksimum"};
	
	private JPanel calcPanel, textPanel;
	
	private MyWindow myWindow;

	public BottomPanel(MyWindow myWindow) {
		this.myWindow = myWindow;
		
		calcPanel = createCalcPanel(CALCULATIONS);
		textPanel = createTextPanel("Uzyskany wynik");
		
		createButtomPanel(calcPanel, textPanel);
	}
	
	private JPanel createCalcPanel(String[] calculations) {
		JPanel calcPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel("Obliczenia");
		JButton resolveButton = new JButton("Oblicz");
		JComboBox calculationComboBox = new JComboBox<>(calculations);

		calcPanel.add(label);
		calcPanel.add(calculationComboBox);
		calcPanel.add(resolveButton);
		
		return calcPanel;

	}
	
	private JPanel createTextPanel(String name) {
		JPanel textPanel = new JPanel(new FlowLayout());
		textPanel.setBorder(BorderFactory.createTitledBorder(
				null, name, TitledBorder.CENTER, TitledBorder.TOP
		));

		JTextArea textArea = new JTextArea(4,20);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(myWindow.getWidth() - 50, 0));
		JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(myWindow.getWidth() - 50, 0));
		Border textBorder = BorderFactory.createLineBorder(Color.BLACK);
		textArea.setBorder(textBorder);
		
		textPanel.add(textArea);
		
		return textPanel;
	}
	
	private void createButtomPanel(JPanel calcPanel, JPanel textPanel) {
		JPanel bottomPanel = new JPanel();
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));  
		bottomPanel.add(calcPanel);
		bottomPanel.add(textPanel);
		
		add(bottomPanel);
		this.setVisible(true);
	}
}
