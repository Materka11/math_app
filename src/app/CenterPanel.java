package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	private MyTableModel myTableModel;
	private LabelPanel labelPanel;
	private BottomPanel bottomPanel;
	
	private MyWindow myWindow;
	
	public CenterPanel(MyWindow myWindow) {
		this.myWindow = myWindow;
		labelPanel = new LabelPanel();
		myTableModel = new MyTableModel(TABLE_VALUES, TABLE_COLUMN_NAMES);
		
		table = createTable(myTableModel);
		scrollPane = createScrollPane(table);
		initGUI();
		buttonsPanel = createButtonsPanel(new JButton[] {addButton, resetButton, fillButton, saveValuesButton});	
		tablePanel = createTablePanel(scrollPane, buttonsPanel);
		
		bottomPanel = new BottomPanel(myWindow, table);
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
	
	private JTable createTable(TableModel myTableModel) {
		table = new JTable(myTableModel);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setEnabled(false);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	    r.setHorizontalAlignment(SwingConstants.RIGHT); 

	    for (int i = 0; i < table.getColumnCount(); i++) {
	        table.getColumnModel().getColumn(i).setCellRenderer(r);
	    }
		
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

	public void resetTable(JTable table, JTextArea textArea) {
		TableModel tableModel = table.getModel();

        int rows = tableModel.getRowCount();
        int cols = tableModel.getColumnCount();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tableModel.setValueAt(0, row, col);
            }
        }

        textArea.setText("Tabela została zresetowana");
	}
	
	public void fillTable(JTable table, JTextArea textArea) {
		TableModel tableModel = table.getModel();

	    Random rand = new Random();
	    int rows = tableModel.getRowCount();
	    int cols = tableModel.getColumnCount();
	    for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < cols; col++) {
	            int randomNumber = rand.nextInt(100);
	            tableModel.setValueAt(randomNumber, row, col);
	        }
	    }

	    textArea.setText("Tabela została wypełniona losowymi liczbami");
	}
	
	public void saveTable(JTable table, JTextArea textArea) {
		Frame parentFrame = JOptionPane.getFrameForComponent(null);

	    FileDialog fileDialog = new FileDialog(parentFrame, "Wybierz miejsce zapisu", FileDialog.SAVE);
	    fileDialog.setFile("Tabela.txt");
	    fileDialog.setLocationRelativeTo(null);
	    fileDialog.setVisible(true);

	    String directory = fileDialog.getDirectory();
	    String fileName = fileDialog.getFile();

	    if (directory != null && fileName != null) {
	        String filePath = directory + fileName;
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
	            for (int row = 0; row < table.getRowCount(); row++) {
	                for (int col = 0; col < table.getColumnCount(); col++) {
	                    writer.write("[" + row + "," + col + "]: ");
	                    writer.write(String.valueOf(table.getValueAt(row, col)));
	                    writer.write("\t");
	                }
	                writer.newLine();
	            }
	            writer.close();
	            textArea.setText("Tabela zapisana do pliku");
	        } catch (IOException e) {
	            System.out.println("Nie można zapisać do pliku");
	            e.printStackTrace();
	        }
	    }
	}
	
	public BottomPanel getBottomPanel() {
		return bottomPanel;
	}
	
	public LabelPanel getLabelPanel() {
		return labelPanel;
	}
	
	public JTable getTable() {
		return table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			addToTable(labelPanel.getColSlider(), labelPanel.getRowSlider(), table, bottomPanel.getTextArea(), labelPanel.getNumberInput());
		}
		if(e.getSource() == resetButton) {
			resetTable(table, bottomPanel.getTextArea());
		}
		if(e.getSource() == fillButton) {
			fillTable(table, bottomPanel.getTextArea());
		}
		if(e.getSource() == saveValuesButton) {
			saveTable(table, bottomPanel.getTextArea());
		}
	}
}
