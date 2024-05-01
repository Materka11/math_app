package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class CenterPanel extends JPanel implements ActionListener {
	private static final Object[][] TABLE_VALUES = {
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 }
		};
	private static final String[] TABLE_COLUMN_NAMES = { "1", "2", "3", "4", "5" };
	
	private JButton addButton, resetButton, fillButton, saveValuesButton;
	private JPanel tablePanel, buttonsPanel;
	private JScrollPane scrollPane;
	private JTable table;
	private LabelPanel labelPanel;
	private BottomPanel bottomPanel;
	
	private MyWindow myWindow;
	
	public CenterPanel(MyWindow myWindow) {
		this.myWindow = myWindow;
		labelPanel = new LabelPanel();
		bottomPanel = new BottomPanel(myWindow, table);
		
		table = createTable(TABLE_VALUES, TABLE_COLUMN_NAMES);
		scrollPane = createScrollPane(table);
		initGUI();
		buttonsPanel = createButtonsPanel(new JButton[] {addButton, resetButton, fillButton, saveValuesButton});	
		tablePanel = createTablePanel(scrollPane, buttonsPanel);
				
		createCenterPanel(labelPanel, tablePanel);
	}
	
	private void initGUI() {
		addButton = myWindow.createButton(null, "DODAJ", "DODAJ", this);
		resetButton = myWindow.createButton(null, "WYZERUJ", "WYZERUJ", this);
		fillButton = myWindow.createButton(null, "WYPEŁNIJ", "WYPEŁNIJ", this);
		saveValuesButton = myWindow.createButton(null, "ZAPISZ", "ZAPISZ", this);
	}
	
	private JPanel createTablePanel(JScrollPane scrollPane, JPanel buttonsPanel) {
		JPanel tablePanel = new JPanel(new FlowLayout());
		
		tablePanel.add(scrollPane);
		tablePanel.add(buttonsPanel);
		
		return tablePanel;
	}
	
	private JTable createTable(Object[][] value, String[] colNames) {
		table = new JTable(value, colNames);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setEnabled(false);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		table.getColumnModel().getColumn(0).setCellRenderer(r);
		table.getColumnModel().getColumn(1).setCellRenderer(r);
		table.getColumnModel().getColumn(2).setCellRenderer(r);
		table.getColumnModel().getColumn(3).setCellRenderer(r);
		table.getColumnModel().getColumn(4).setCellRenderer(r);
		
		return table;
	}
	
	private JScrollPane createScrollPane(JTable table) {
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setPreferredSize(new Dimension(450, 110));
		
		return scrollPane;
	}
	
	private JPanel createButtonsPanel(JButton[] buttons) {
		JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));
		
		for (JButton button : buttons) {
			buttonsPanel.add(button);
		}
		
		return buttonsPanel;
	}
	
	private void createCenterPanel(JPanel labelPanel, JPanel tablePanel) {
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		centerPanel.add(labelPanel, BorderLayout.NORTH);
	    centerPanel.add(tablePanel, BorderLayout.CENTER);
	    centerPanel.add(bottomPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void addToTable(JSlider colSlider, JSlider rowSlider, JTable table, JTextArea textArea, JTextField numberInput) {
		int row = colSlider.getValue() - 1;
	    int column = rowSlider.getValue() - 1;
	    
	    try {
	        int number = Integer.parseInt(numberInput.getText());
	        TableModel model = table.getModel();
	        model.setValueAt(number, row, column);
	        textArea.setText("Liczba "+ number + " została dodana do tabeli.");
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(textArea, "Podany znak nie jest liczbą.");
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			addToTable(labelPanel.getColSlider(), labelPanel.getRowSlider(), table, bottomPanel.getTextArea(), labelPanel.getNumberInput());
		}
		
	}
}
