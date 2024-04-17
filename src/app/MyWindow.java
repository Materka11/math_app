package app;

import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class MyWindow extends JFrame implements ActionListener {
	
	private final int HEIGHT_WINDOW = 450;
	private final int WIDTH_WINDOW = 650;
	private static final String ICON_PATH = "/resources/";
	
	private JMenu menuFile, menuDisplay, menuCalc, menuHelp;

	private ImageIcon printIcon, exitIcon, helpIcon, infoIcon;
	private JButton saveButton, printButton, exitButton, sumButton, averageButton, minButton, maxButton, appInfoButton, authInfoButton;
	
	private JPanel contentPane, centerPanel, labelPanel;
	private JLabel numberInputLabel, rowSliderLabel, colSliderLabel; 
	private JTextField numberInput;
	private JSlider rowSlider, colSlider;
	
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

	    String[] fileMenuItems = {"Zapisz", "Wyjście"};
	    menuFile = createMenu("Plik", fileMenuItems);

	    String[] displayMenuItems = {"Pełny ekran"};
	    menuDisplay = createMenu("Widok", displayMenuItems);

	    String[] calcMenuItems = {"Suma", "Średnia", "Wartość Minimalna", "Wartość Maksymalna"};
	    menuCalc = createMenu("Obliczenia", calcMenuItems);

	    String[] helpMenuItems = {"Informacje o aplikacji", "Informacje o autorze"};
	    menuHelp = createMenu("Pomoc", helpMenuItems);
	    
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
			labelPanel, 
			new JLabel[] {numberInputLabel, rowSliderLabel, colSliderLabel}, numberInput, 
			new JSlider[] {rowSlider, colSlider}
		);
		
		createCenterPanel(centerPanel, labelPanel);
	

		
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
	
	private JPanel createLabelPanel(JPanel labelPanel, JLabel[] labels, JTextField input, JSlider[] sliders) {
		labelPanel = new JPanel(new FlowLayout());
		
		labelPanel.add(labels[0]);
		labelPanel.add(input);
		labelPanel.add(labels[1]);
		labelPanel.add(sliders[0]);
		labelPanel.add(labels[2]);
		labelPanel.add(sliders[1]);
		
		add(labelPanel);
		
		return labelPanel;
	}
	
	private void createCenterPanel(JPanel centerPanel, JPanel labelPanel) {
		centerPanel = new JPanel(new FlowLayout());
		centerPanel.add(labelPanel);
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
