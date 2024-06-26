package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

public class CenterPanel extends JPanel implements ActionListener {
	private static final Object[][] TABLE_VALUES = {
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 }
		};
	private static final String[] TABLE_COLUMN_NAMES = { "1", "2", "3", "4", "5" };
	
	private JButton addButton, resetButton, fillButton, saveValuesButton, generateChartButton;
	private JPanel tablePanel, buttonsPanel, calendarPanel;
	private JScrollPane scrollPane;
	private JTable table;
	private MyTableModel myTableModel;
	private LabelPanel labelPanel;
	private BottomPanel bottomPanel;
	private JDateChooser dateChooser;
	
	private MyWindow myWindow;
	
	public CenterPanel(MyWindow myWindow) {
		this.myWindow = myWindow;
		labelPanel = new LabelPanel();
		myTableModel = new MyTableModel(TABLE_VALUES, TABLE_COLUMN_NAMES);
		
		table = createTable(myTableModel);
		scrollPane = createScrollPane(table);
		initGUI();
		calendarPanel = createCalendarPanel(dateChooser);
		buttonsPanel = createButtonsPanel(new JButton[] {addButton, resetButton, fillButton, saveValuesButton, generateChartButton});
		tablePanel = createTablePanel(scrollPane, buttonsPanel);
		bottomPanel = new BottomPanel(myWindow, table);
		createCenterPanel(labelPanel, tablePanel, bottomPanel);
	}
	
	private void initGUI() {
		addButton = myWindow.createButton(null, "DODAJ", "DODAJ", this);
		resetButton = myWindow.createButton(null, "WYZERUJ", "WYZERUJ", this);
		fillButton = myWindow.createButton(null, "WYPEŁNIJ", "WYPEŁNIJ", this);
		saveValuesButton = myWindow.createButton(null, "ZAPISZ", "ZAPISZ", this);
		
		dateChooser = new JDateChooser();
	       dateChooser.setDateFormatString("yyyy-MM-dd");
	       dateChooser.addPropertyChangeListener("date", evt -> {
	           Date selectedDate = dateChooser.getDate();
	           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	           String dateString = sdf.format(selectedDate);
	           bottomPanel.getTextArea().setText("Wybrana data: " + dateString);
	       });
	       
	    generateChartButton = myWindow.createButton(null, "GENERUJ WYKRES", "GENERUJ_WYKRES", this);
	}
	
	private JPanel createCalendarPanel(JDateChooser dataChooser) {
		JPanel calendarPanel = new JPanel(new BorderLayout());
		
		calendarPanel.add(dateChooser, BorderLayout.CENTER);
		
		return calendarPanel;
	}
	
	private JPanel createTablePanel(JScrollPane scrollPane, JPanel buttonsPanel) {
		JPanel tablePanel = new JPanel();
	    GroupLayout layout = new GroupLayout(tablePanel);
	    tablePanel.setLayout(layout);

	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);

	    layout.setHorizontalGroup(
	        layout.createSequentialGroup()
	            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addComponent(buttonsPanel)
	    );

	    layout.setVerticalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            .addComponent(buttonsPanel)
	    );

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
		JPanel buttonsPanel = new JPanel(new GridLayout(6, 1));
		
		for (JButton button : buttons) {
			buttonsPanel.add(button);
		}
		buttonsPanel.add(calendarPanel);
		
		return buttonsPanel;
	}
	
	private void createCenterPanel(JPanel labelPanel, JPanel tablePanel, JPanel bottomPanel) {
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		centerPanel.add(labelPanel, BorderLayout.NORTH);
	    centerPanel.add(tablePanel, BorderLayout.CENTER);
	    centerPanel.add(bottomPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void generateChart(JTable table) {
	    HistogramDataset dataset = new HistogramDataset();
	    int rowCount = table.getRowCount();
	    int columnCount = table.getColumnCount();
	    double[] values = new double[rowCount * columnCount];
	    int index = 0;

	    for (int i = 0; i < rowCount; i++) {
	        for (int j = 0; j < columnCount; j++) {
	            values[index++] = ((Number) table.getValueAt(i, j)).doubleValue();
	        }
	    }

	    dataset.addSeries("Histogram", values, 10);

	    JFreeChart histogram = ChartFactory.createHistogram(
	        "Histogram",
	        "Wartości",
	        "Częstotliwość",
	        dataset,
	        PlotOrientation.VERTICAL,
	        false,
	        false,
	        false
	    );

	    ChartPanel chartPanel = new ChartPanel(histogram);
	    chartPanel.setPreferredSize(new Dimension(800, 600));
	    JFrame chartFrame = new JFrame();
	    chartFrame.setContentPane(chartPanel);
	    chartFrame.pack();
	    chartFrame.setVisible(true);
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
		if(e.getSource() == generateChartButton) {
	        generateChart(table);
	    }
	}
}
