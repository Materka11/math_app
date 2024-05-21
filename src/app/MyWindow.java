package app;

import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

public class MyWindow extends JFrame {
	private final int HEIGHT_WINDOW = 550;
	private final int WIDTH_WINDOW = 1000;
	
	private static final String[] TIPS_OF_THE_DAY = {
			"Pamiętaj, aby regularnie zapisywać swoją pracę!",
		    "Zrób sobie przerwę od pracy i zrelaksuj się przez chwilę.",
		    "Spróbuj podejść do problemu z innej perspektywy.",
		    "Ustal konkretne cele na dzisiaj i pracuj systematycznie, aby je osiągnąć.",
		    "Podziel swoje zadania na mniejsze kroki, aby łatwiej było je wykonać.",
		    "Zadbaj o zdrowie psychiczne - nie bagatelizuj stresu, szukaj sposobów na jego redukcję.",
		    "Nawilżaj oczy i wykonuj ćwiczenia dla oczu, aby zmniejszyć zmęczenie wzroku.",
		    "Nie bój się prosić o pomoc, gdy jesteś zdezorientowany lub potrzebujesz wsparcia.",
		    "Rozwijaj swoje umiejętności poprzez regularne naukę i praktykę.",
		    "Pamiętaj, że każdy ma gorsze dni - nie zniechęcaj się porażkami, ale ucz się na nich.",
		    "Dbaj o higienę snu i staraj się regularnie spać wystarczającą liczbę godzin.",
		    "Zachowuj równowagę między pracą a życiem prywatnym, unikaj nadmiernego przepracowywania się.",
		    "Bądź otwarty na konstruktywną krytykę i szukaj sposobów na ciągłe doskonalenie się.",
		    "Pamiętaj o wdzięczności - doceniaj małe sukcesy i rzeczy, za które jesteś wdzięczny.",
		    "Szukaj inspiracji w codziennych sytuacjach i otaczających Cię osobach.",
		    "Pozwól sobie na eksperymentowanie i popełnianie błędów - to część procesu uczenia się.",
		    "Staraj się być obecny w chwili obecnej i ciesz się drobnymi przyjemnościami dnia codziennego.",
		    "Pamiętaj, że nawet najtrudniejsze wyzwania mogą prowadzić do największych sukcesów.",
		    "Zachowaj pozytywne podejście i szukaj światła nawet w najciemniejszych chwilach.",
		    "Dziel się swoją wiedzą i doświadczeniem z innymi, aby tworzyć lepsze środowisko pracy.",
		    "Dbaj o równowagę pomiędzy pracą a odpoczynkiem, aby uniknąć wypalenia zawodowego.",
		    "Bądź elastyczny i otwarty na zmiany - one są częścią procesu rozwoju.",
		    "Zachowuj empatię i szacunek wobec innych, nawet gdy nie zgadzasz się z ich punktem widzenia.",
		    "Podziel się uśmiechem z innymi - to proste działanie może poprawić nastrój całego dnia.",
	    };
	
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
		
		centerPanel = new CenterPanel(this);	
		add(centerPanel, BorderLayout.CENTER);
		
		menuBar = new MenuBar(this, centerPanel);
		setJMenuBar(menuBar);
		
		toolBar = new ToolBar(this, centerPanel, menuBar);
		add(toolBar, BorderLayout.NORTH);
		
		navigationPanel = new NavigationPanel(toolBar, centerPanel, menuBar);
		add(navigationPanel, BorderLayout.WEST);
		
		statusPanel = new StatusPanel(centerPanel);
		add(statusPanel, BorderLayout.SOUTH);
		
		showTipOfTheDay();
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
	
	private void showTipOfTheDay() {
		DefaultTipModel listOfTips = new DefaultTipModel();
        
		for (String tip : TIPS_OF_THE_DAY) {
            listOfTips.add(new DefaultTip(tip, tip));
        }
		
		JTipOfTheDay tipOfTheDay = new JTipOfTheDay(listOfTips);
		tipOfTheDay.showDialog(this);
    }
}
