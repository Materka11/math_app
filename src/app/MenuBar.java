package app;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MenuBar extends JMenuBar implements ActionListener {
	private static final String[] FILE_MENU_ITEMS = {"Zapisz","Drukuj","Wyjście"};
	private static final String[] DISPLAY_MENU_ITEMS = {"Pełny ekran"};
	private static final String[] CALC_MENU_ITEMS = {"Suma", "Średnia", "Wartość Minimalna", "Wartość Maksymalna"};
	private static final String[] HELP_MENU_ITEMS = {"Informacje o aplikacji", "Informacje o autorze"};
	
	private JMenu menuFile, menuDisplay, menuCalc, menuHelp;
	
	private MyWindow myWindow;
	private CenterPanel centerPanel;
	private JTable table;
	private JTextArea textArea;
	private BottomPanel bottomPanel;
	
	public MenuBar(MyWindow myWindow, CenterPanel centerPanel) {
		this.myWindow = myWindow;
		this.centerPanel = centerPanel;
		bottomPanel = centerPanel.getBottomPanel();
		table = centerPanel.getTable();
		textArea = bottomPanel.getTextArea();
		
		
		initGUI();
		
		add(menuFile);
		add(menuDisplay);
		add(menuCalc);
		add(menuHelp);
	}
	
	private void initGUI() {
		menuFile = createMenu("Plik", FILE_MENU_ITEMS);
	    menuDisplay = createMenu("Widok", DISPLAY_MENU_ITEMS);
	    menuCalc = createMenu("Obliczenia", CALC_MENU_ITEMS);
	    menuHelp = createMenu("Pomoc", HELP_MENU_ITEMS);
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
	
	public void printTable(JTable table) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(table.getPrintable(null, null, null));

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
	
	public void toggleFullScreen(MyWindow myWindow) {
        myWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
	public void showInfoDialog() {
	     String message = "Nazwa aplikacji: Moja Aplikacja\nWersja: 1.0\nAutor: Arkadiusz Materka\nData wydania: 12-05-2024";
	     JOptionPane.showMessageDialog(this, message, "Informacje o aplikacji", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showHelpDialog() {
	     String message = "Witaj w aplikacji!\n\nPrzy pomocy przycisków w interfejsie jesteś w stanie manipulować danymi w tabeli oraz wyświetlać komunikaty w polu tekstowym.";
	     JOptionPane.showMessageDialog(this, message, "Pomoc", JOptionPane.INFORMATION_MESSAGE);
	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Wyjście")) {
			myWindow.closeWindow();
		}
		if(e.getActionCommand().equals("Zapisz")) {
			centerPanel.saveTable(table, textArea);
		}
		if(e.getActionCommand().equals("Drukuj")) {
			printTable(table);
		}
		if(e.getActionCommand().equals("Pełny ekran")) {
			toggleFullScreen(myWindow);
		}
		if(e.getActionCommand().equals("Suma")) {
			int sum = bottomPanel.getSum(table);
			textArea.setText("Suma elementów wynosi: " + sum);
		}
		if(e.getActionCommand().equals("Średnia")) {
			double average = bottomPanel.getAverage(table);
			textArea.setText("Średnia wartość elementów wynosi: " + average);
		}
		if(e.getActionCommand().equals("Wartość Minimalna")) {
			int min = bottomPanel.getMin(table);
			textArea.setText("Najmniejsza wartość w tabeli to: " + min);
		}
		if(e.getActionCommand().equals("Wartość Maksymalna")) {
			int max = bottomPanel.getMax(table);
			textArea.setText("Największa wartość w tabeli to: " + max);
		}
		if(e.getActionCommand().equals("Informacje o aplikacji")) {
			showHelpDialog();
		}
		if(e.getActionCommand().equals("Informacje o autorze")) {
			showInfoDialog();
		}
	}
}
