package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

public class BottomPanel extends JPanel implements ActionListener {
	private static final String[] CALCULATIONS = {"Suma","Średnia","Minimum","Maksimum"};
	
	private JPanel calcPanel, textPanel;
	private JComboBox calculationComboBox;
	private JTextArea textArea;
	
	private MyWindow myWindow;
	private JTable table;
	
	public BottomPanel(MyWindow myWindow, JTable table) {
		this.myWindow = myWindow;
		this.table = table;
		
		calcPanel = createCalcPanel(CALCULATIONS);
		textPanel = createTextPanel("Uzyskany wynik");
		
		createButtomPanel(calcPanel, textPanel);
	}
	
	private JPanel createCalcPanel(String[] calculations) {
		JPanel calcPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel label = new JLabel("Obliczenia");
		
		JButton resolveButton = new JButton("Oblicz");
		resolveButton.addActionListener(this);
		
		calculationComboBox = new JComboBox<>(calculations);

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

		textArea = new JTextArea(4,20);
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
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public int getSum(JTable table) {
		int sum = 0;
		TableModel model = table.getModel();
		int rowCount = model.getRowCount();
		int columnCount = model.getColumnCount();

		for (int i = 0; i < rowCount; i++) {
		    for (int j = 0; j < columnCount; j++) {
		        sum += (int) model.getValueAt(i, j);
		    }
		}
		
		return sum;
	}
	
	public double getAverage(JTable table) {
		int sum = getSum(table);
		double average = (double) sum / (table.getRowCount() * table.getColumnCount());
		
		return average;
	}
	

	
	public int getMin(JTable table) {
		int min = Integer.MAX_VALUE;
		TableModel model = table.getModel();
	    int rowCount = model.getRowCount();
	    int columnCount = model.getColumnCount();
	    
	    for (int i = 0; i < rowCount; i++) {
	        for (int j = 0; j < columnCount; j++) {
	            int value = (int) model.getValueAt(i, j);
	            if (value < min) {
	                min = value;
	            }
	        }
	    }
	    
	    return min;
	}
	
	public int getMax(JTable table) {
		int max = Integer.MIN_VALUE;
		TableModel model = table.getModel();
	    int rowCount = model.getRowCount();
	    int columnCount = model.getColumnCount();
	    
	    for (int i = 0; i < rowCount; i++) {
	        for (int j = 0; j < columnCount; j++) {
	            int value = (int) model.getValueAt(i, j);
	            if (value > max) {
	                max = value;
	            }
	        }
	    }
	    
	    return max;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = calculationComboBox.getSelectedIndex();
        switch (index) {
            case 0:
            	int sum = getSum(table);
				textArea.setText("Suma elementów wynosi: " + sum);
                break;
            case 1:
            	double average = getAverage(table);
				textArea.setText("Średnia wartość elementów wynosi: " + average);
                break;
            case 2:
            	int min = getMin(table);
				textArea.setText("Najmniejsza wartość w tabeli to: " + min);
                break;
            case 3:
            	int max = getMax(table);
				textArea.setText("Największa wartość w tabeli to: " + max);
                break;
        }
		
	}
}
