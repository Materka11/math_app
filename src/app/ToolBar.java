package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class ToolBar extends JPanel implements ActionListener {
	private static final String ICON_PATH = "/resources/";
	private static final int ICON_WIDTH = 24;
	
	private ImageIcon saveIcon, printIcon, exitIcon, sumIcon, averageIcon, minIcon, maxIcon, helpIcon, infoIcon;
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
		saveIcon = getResource("diskette.png");
		printIcon = getResource("printer.png");
		exitIcon = getResource("logout.png");
		sumIcon = getResource("symbol.png");
		averageIcon = getResource("average.png");
		minIcon = getResource("min.png");
		maxIcon = getResource("plus.png");
		helpIcon = getResource("question-mark.png");
		infoIcon = getResource("letter-i.png");
		
		saveIcon.setImage(saveIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
	    printIcon.setImage(printIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
	    exitIcon.setImage(exitIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
	    sumIcon.setImage(sumIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
		averageIcon.setImage(averageIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
		minIcon.setImage(minIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
		maxIcon.setImage(maxIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
	    helpIcon.setImage(helpIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
	    infoIcon.setImage(infoIcon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
		
		saveButton = myWindow.createButton(saveIcon, "zapisz", "ZAPISZ PLIK", this);
		printButton = myWindow.createButton(printIcon, "drukuj", "DRUKUJ PLIK", this);
		exitButton = myWindow.createButton(exitIcon, "wyjście", "WYJŚCIE", this);	
		sumButton = myWindow.createButton(sumIcon, "Σ", "SUMUJ WARTOŚĆI TABELI", this);
		averageButton = myWindow.createButton(averageIcon, "x̅", "ŚREDNIA WARTOŚĆ KOMÓREK W TABELI", this);
		minButton = myWindow.createButton(minIcon, "MIN", "NAJMNIEJSZA WARTOŚĆ", this);
		maxButton = myWindow.createButton(maxIcon, "MAX", "NAJWIĘKSZA WARTOŚĆ W TABELI", this);
		helpButton = myWindow.createButton(helpIcon, "pomoc", "POMOC", this);
		infoButton = myWindow.createButton(infoIcon, "informacje", "INFORMACJE", this);
	}
	
	private ImageIcon getResource(String resource) {
		return new ImageIcon(MyWindow.class.getResource(ICON_PATH + resource));
	}
	
	private JToolBar createToolBar(JButton[] buttons) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		for (int i = 0; i < buttons.length; i++) {
			toolBar.add(buttons[i]);
			if (i == 2 || i == 6) {
	            toolBar.addSeparator();
	        }
		}
		
		return toolBar;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) {
			myWindow.closeWindow();
		}
		if(e.getSource() == sumButton) {
			int sum = bottomPanel.getCalculationController().getCalculationModel().getSum(table);
			textArea.setText("Suma elementów wynosi: " + sum);
		}
		if(e.getSource() == averageButton) {
			double average = bottomPanel.getCalculationController().getCalculationModel().getAverage(table);
			textArea.setText("Średnia wartość elementów wynosi: " + average);
		}
		if(e.getSource() == minButton) {
			int min = bottomPanel.getCalculationController().getCalculationModel().getMin(table);
			textArea.setText("Najmniejsza wartość w tabeli to: " + min);
		}
		if(e.getSource() == maxButton) {
			int max = bottomPanel.getCalculationController().getCalculationModel().getMax(table);
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
