package app;

import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class MyWindow extends JFrame implements ActionListener {
	private final int HEIGHT_WINDOW = 450;
	private final int WIDTH_WINDOW = 650;
	
	private JPanel contentPane;
	
	private MenuBar menuBar;
	private ToolBar toolBar;
	private CenterPanel centerPanel;
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

		menuBar = new MenuBar(this);
		setJMenuBar(menuBar);
	    
		toolBar = new ToolBar(this);
		add(toolBar, BorderLayout.NORTH);
		
		centerPanel = new CenterPanel(this);	
		add(centerPanel, BorderLayout.CENTER);
		
		statusPanel = new StatusPanel();
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

	@Override
	public void actionPerformed(ActionEvent e) {
//		if(e.getActionCommand().equals("Wyjście") || e.getSource() == exitButton) {
//			closeWindow();
//		}
	}
}
