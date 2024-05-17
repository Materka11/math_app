package app;

import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.l2fprod.common.swing.JTaskPane;

public class MyWindow extends JFrame {
	private final int HEIGHT_WINDOW = 550;
	private final int WIDTH_WINDOW = 850;
	
	private JPanel contentPane;
	
	private MenuBar menuBar;
	private ToolBar toolBar;
	private CenterPanel centerPanel;
	private StatusPanel statusPanel;
	private NavigationPanel navigationPanel;
	
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
		
		navigationPanel = new NavigationPanel();
		add(navigationPanel, BorderLayout.WEST);
		
		centerPanel = new CenterPanel(this);	
		add(centerPanel, BorderLayout.CENTER);
		
		menuBar = new MenuBar(this, centerPanel);
		setJMenuBar(menuBar);
		
		toolBar = new ToolBar(this, centerPanel, menuBar);
		add(toolBar, BorderLayout.NORTH);
		
		statusPanel = new StatusPanel(centerPanel);
		add(statusPanel, BorderLayout.SOUTH);
	};
	
	private void createContentPane(JPanel cp) {
		cp = (JPanel) getContentPane();
		cp.setLayout(new BorderLayout());
		setContentPane(cp);
	}
	
	public static void main(String[] args) {
		MyWindow myWindow = new MyWindow();
		myWindow.setVisible(true);
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
	
	public JButton createButton(ImageIcon icon, String name, String tooltipText, ActionListener listener) {
		JButton button;
		
		if(icon != null) {
			button = new JButton(icon);
			button.setToolTipText(tooltipText);
		} else {
			button = new JButton(name);
			button.setToolTipText(tooltipText);
		}
		
		button.addActionListener(listener);
		
		return button;
	}
	
	public int getWidthWindow() {
		return WIDTH_WINDOW;
	}
}
