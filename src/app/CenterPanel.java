package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

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
	private JPanel labelPanel, tablePanel, buttonsPanel;
	private JScrollPane scrollPane;
	private JTable table;
	
	private MyWindow myWindow;

	public CenterPanel(MyWindow myWindow) {
		this.myWindow = myWindow;
		labelPanel = new LabelPanel();
		
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
		add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
