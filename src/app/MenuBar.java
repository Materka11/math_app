package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener {
	private static final String[] FILE_MENU_ITEMS = {"Zapisz", "Wyjście"};
	private static final String[] DISPLAY_MENU_ITEMS = {"Pełny ekran"};
	private static final String[] CALC_MENU_ITEMS = {"Suma", "Średnia", "Wartość Minimalna", "Wartość Maksymalna"};
	private static final String[] HELP_MENU_ITEMS = {"Informacje o aplikacji", "Informacje o autorze"};
	
	private JMenu menuFile, menuDisplay, menuCalc, menuHelp;
	
	private MyWindow myWindow;
	
	public MenuBar(MyWindow myWindow) {
		this.myWindow = myWindow;
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Wyjście")) {
			myWindow.closeWindow();
		}
		
	}
}
