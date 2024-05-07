package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class ToolBar extends JPanel implements ActionListener {
	private static final String ICON_PATH = "/resources/";
	
	private ImageIcon printIcon, exitIcon, helpIcon, infoIcon;
	private JButton saveButton, printButton, exitButton, sumButton, averageButton, minButton, maxButton, helpButton, infoButton;
	
	private JToolBar toolBar;
	
	private MyWindow myWindow;
	private CenterPanel centerPanel;
	private BottomPanel bottomPanel;
	private JTable table;
	private JTextArea textArea;
	private MenuBar menuBar;
	
	public ToolBar(MyWindow myWindow, CenterPanel centerPanel, MenuBar menuBar) {
		this.myWindow = myWindow;
		this.centerPanel = centerPanel;
		bottomPanel = centerPanel.getBottomPanel();
		table = centerPanel.getTable();
		this.menuBar = menuBar;
		
		setLayout(new BorderLayout());
		initGUI();
		textArea = bottomPanel.getTextArea();

		toolBar = createToolBar(
			new JButton[] {
				saveButton, printButton, exitButton, sumButton, averageButton, minButton, maxButton, helpButton, infoButton
			}
		);
		add(toolBar, BorderLayout.CENTER);
	}
	
	private void initGUI() {
		printIcon = getResource("print.jpg");
		exitIcon = getResource("close.jpg");
		helpIcon = getResource("help_context.jpg");
		infoIcon = getResource("about.jpg");

		saveButton = myWindow.createButton(null, "zapisz", "ZAPISZ PLIK", this);
		printButton = myWindow.createButton(printIcon, null, null, this);
		exitButton = myWindow.createButton(exitIcon, null, null, this);	
		sumButton = myWindow.createButton(null, "Σ", "SUMUJ WARTOŚĆI TABELI", this);
		averageButton = myWindow.createButton(null, "x̅", "ŚREDNIA WARTOŚĆ KOMUREK W TABELI", this);
		minButton = myWindow.createButton(null, "MIN", "NAJMNIEJSZA WARTOŚĆ", this);
		maxButton = myWindow.createButton(null, "MAX", "NAJWIĘKSZA WARTOŚĆ W TABELI", this);
		helpButton = myWindow.createButton(helpIcon, null, null, this);
		infoButton = myWindow.createButton(infoIcon, null, null, this);
	}
	
	private ImageIcon getResource(String resource) {
		return new ImageIcon(MyWindow.class.getResource(ICON_PATH + resource));
	}
	
	private JToolBar createToolBar(JButton[] buttons) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		for (JButton button : buttons) {
			toolBar.add(button);
		}
		
		return toolBar;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) {
			myWindow.closeWindow();
		}
		if(e.getSource() == sumButton) {
			int sum = bottomPanel.getSum(table);
			textArea.setText("Suma elementów wynosi: " + sum);
		}
		if(e.getSource() == averageButton) {
			double average = bottomPanel.getAverage(table);
			textArea.setText("Średnia wartość elementów wynosi: " + average);
		}
		if(e.getSource() == minButton) {
			int min = bottomPanel.getMin(table);
			textArea.setText("Najmniejsza wartość w tabeli to: " + min);
		}
		if(e.getSource() == maxButton) {
			int max = bottomPanel.getMax(table);
			textArea.setText("Największa wartość w tabeli to: " + max);
		}
		if(e.getSource() == saveButton) {
			centerPanel.saveTable(table, textArea);
		}
		if(e.getSource() == printButton) {
	        menuBar.printTable(table);
	    }
		if (e.getSource() == helpButton) {
	        menuBar.showHelpDialog();
	    }
	    if (e.getSource() == infoButton) {
	        menuBar.showInfoDialog();
	    }
	}
}
