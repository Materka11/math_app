package app;

import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;


public class MyWindow extends JFrame implements ActionListener {
	
	private final int HEIGHT_WINDOW = 450;
	private final int WIDTH_WINDOW = 650;
	
	private static final String ICON_PATH = "/resources/";
	
	private static final String[] FILE_MENU_ITEMS = {"Zapisz", "Wyjście"};
	private static final String[] DISPLAY_MENU_ITEMS = {"Pełny ekran"};
	private static final String[] CALC_MENU_ITEMS = {"Suma", "Średnia", "Wartość Minimalna", "Wartość Maksymalna"};
	private static final String[] HELP_MENU_ITEMS = {"Informacje o aplikacji", "Informacje o autorze"};
	
	private static final Object[][] TABLE_VALUES = {
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 }
		};
	private static final String[] TABLE_COLUMN_NAMES = { "1", "2", "3", "4", "5" };
	
	private JMenu menuFile, menuDisplay, menuCalc, menuHelp;

	private ImageIcon printIcon, exitIcon, helpIcon, infoIcon;
	private JButton saveButton, printButton, exitButton, sumButton, averageButton, minButton, maxButton, appInfoButton, authInfoButton, addButton, resetButton, fillButton, saveValuesButton;
	
	private JPanel contentPane, centerPanel, labelPanel, tablePanel, buttonsPanel;
	private JLabel numberInputLabel, rowSliderLabel, colSliderLabel; 
	private JTextField numberInput;
	private JSlider rowSlider, colSlider;
	private JTable table;
	private JScrollPane scrollPane;
	
	private StatusPanel statusPanel;
	
	public MyWindow() {
		setTitle("MyWindow v.1.0.1");
		setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}
		});
		
		createContentPane(contentPane);

		
	    menuFile = createMenu("Plik", FILE_MENU_ITEMS);
	    menuDisplay = createMenu("Widok", DISPLAY_MENU_ITEMS);
	    menuCalc = createMenu("Obliczenia", CALC_MENU_ITEMS);
	    menuHelp = createMenu("Pomoc", HELP_MENU_ITEMS);
	    
	    createMenuBar(new JMenu[]{menuFile, menuDisplay, menuCalc, menuHelp});
	    
	
		
		printIcon = getResource("print.jpg");
		exitIcon = getResource("close.jpg");
		helpIcon = getResource("help_context.jpg");
		infoIcon = getResource("about.jpg");

		saveButton = createButton(null, "zapisz", "ZAPISZ PLIK");
		printButton = createButton(printIcon, null, null);
		exitButton = createButton(exitIcon, null, null);	
		sumButton = createButton(null, "Σ", "SUMUJ WARTOŚĆI TABELI");
		averageButton = createButton(null, "x̅", "ŚREDNIA WARTOŚĆ KOMUREK W TABELI");
		minButton = createButton(null, "MIN", "NAJMNIEJSZA WARTOŚĆ");
		maxButton = createButton(null, "MAX", "NAJWIĘKSZA WARTOŚĆ W TABELI");
		appInfoButton = createButton(helpIcon, null, null);
		authInfoButton = createButton(infoIcon, null, null);

		createToolBar(
			new JButton[] {saveButton, printButton, exitButton, sumButton, averageButton, minButton, maxButton, appInfoButton, authInfoButton}
		);
		
		
		
		numberInputLabel = createlabel("Wprowadź liczbę");
		numberInput = createTextInput("0", 100, 20);
		
		rowSliderLabel = createlabel("Numer wiersza");
		rowSlider = createSlider(1, 5, 3, 100, 40);
		
		colSliderLabel = createlabel("Numer kolumny");
		colSlider = createSlider(1, 5, 3, 100, 40);
		
		labelPanel = createLabelPanel(
			new JLabel[] {numberInputLabel, rowSliderLabel, colSliderLabel}, numberInput, 
			new JSlider[] {rowSlider, colSlider}
		);
			
		
		
		table = createTable(TABLE_VALUES, TABLE_COLUMN_NAMES);
				
		scrollPane = createScrollPane(table);
		
		
		addButton = createButton(null, "DODAJ", "DODAJ");
		resetButton = createButton(null, "WYZERUJ", "WYZERUJ");
		fillButton = createButton(null, "WYPEŁNIJ", "WYPEŁNIJ");
		saveValuesButton = createButton(null, "ZAPISZ", "ZAPISZ");

		buttonsPanel = createButtonsPanel(new JButton[] {addButton, resetButton, fillButton, saveValuesButton});
				
		tablePanel = createTablePanel(scrollPane, buttonsPanel);
				
		createCenterPanel(labelPanel, tablePanel);
		

		
		statusPanel = new StatusPanel();
		
		add(statusPanel, BorderLayout.SOUTH);
	};
	
	private void createContentPane(JPanel cp) {
		cp = (JPanel) getContentPane();
		cp.setLayout(new BorderLayout());
		setContentPane(cp);
	}
	
	private ImageIcon getResource(String resource) {
		return new ImageIcon(MyWindow.class.getResource(ICON_PATH + resource));
	}
	
	private JMenu createMenu(String menuName, String[] menuItems) {
	    JMenu menu = new JMenu(menuName);

	    for (String menuItemText : menuItems) {
	        JMenuItem menuItem = new JMenuItem(menuItemText);
	        menu.add(menuItem);
	        menuItem.addActionListener(this);
	    }

	    return menu;
	}
	
	private void createMenuBar(JMenu[] menus) {
		JMenuBar menuBar = new JMenuBar();
		
		for(JMenu menu : menus) {
			menuBar.add(menu);
		}
		
	    setJMenuBar(menuBar);
	}
	
	private JButton createButton(ImageIcon icon, String name, String tooltipText) {
		JButton button;
		
		if(icon != null) {
			button = new JButton(icon);
		} else {
			button = new JButton(name);
			button.setToolTipText(tooltipText);
		}
		
		button.addActionListener(this);
		
		return button;
	}
	
	private void createToolBar(JButton[] buttons) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		for (JButton button : buttons) {
			toolBar.add(button);
		}
		
		add(toolBar, BorderLayout.NORTH);
	}
	
	private JLabel createlabel(String text) {
		JLabel label = new JLabel(text);
		
		return label;
	}
	
	private JTextField createTextInput(String defaultValue, int width, int height) {
		JTextField input = new JTextField();
		input.setText(defaultValue);
		input.setPreferredSize(new Dimension(width, height));
		
		return input;
	}
	
	private JSlider createSlider(int minValue, int maxValue, int value, int width, int height) {
		JSlider slider = new JSlider(minValue, maxValue, value);
		slider.setPreferredSize(new Dimension(width, height));
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		return slider;
	}
	
	private JPanel createLabelPanel(JLabel[] labels, JTextField input, JSlider[] sliders) {
		JPanel labelPanel = new JPanel(new FlowLayout());
		
		labelPanel.add(labels[0]);
		labelPanel.add(input);
		labelPanel.add(labels[1]);
		labelPanel.add(sliders[0]);
		labelPanel.add(labels[2]);
		labelPanel.add(sliders[1]);
		
		add(labelPanel);
		
		return labelPanel;
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
		JPanel centerPanel = new JPanel(new FlowLayout());
		centerPanel.add(labelPanel);
		centerPanel.add(tablePanel);
		add(centerPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void closeWindow() {
		int value = JOptionPane.showOptionDialog(
				this,
				"Czy chcesz zamknąć aplikację",
				"Uwaga",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				new String[] {"Tak", "Nie"},
				"Tak");
		
		
		if(value == JOptionPane.YES_OPTION) {
			dispose();
			System.exit(0);
		}
		
	}
	
	public static void main(String[] args) {
		MyWindow myWindow = new MyWindow();
		myWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Wyjście") || e.getSource() == exitButton) {
			closeWindow();
		}
	}
}
