package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBar extends JPanel implements ActionListener {
	private static final String ICON_PATH = "/resources/";
	
	private ImageIcon printIcon, exitIcon, helpIcon, infoIcon;
	private JButton saveButton, printButton, exitButton, sumButton, averageButton, minButton, maxButton, appInfoButton, authInfoButton;
	
	private JToolBar toolBar;
	
	private MyWindow myWindow;
	
	public ToolBar(MyWindow myWindow) {
		this.myWindow = myWindow;
		
		setLayout(new BorderLayout());
		initGUI();

		toolBar = createToolBar(
			new JButton[] {
				saveButton, printButton, exitButton, sumButton, averageButton, minButton, maxButton, appInfoButton, authInfoButton
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
		appInfoButton = myWindow.createButton(helpIcon, null, null, this);
		authInfoButton = myWindow.createButton(infoIcon, null, null, this);
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
	}
}