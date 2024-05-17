package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;


public class NavigationPanel extends JPanel {
	private static final int ICON_WIDTH = 16;
	
	private JTaskPane taskPane;
	
	private ToolBar toolBar;
	private CenterPanel centerPanel;
	private MenuBar menuBar;
	private BottomPanel bottomPanel;
	private JTable table;
	private JTextArea textArea;

    public NavigationPanel(ToolBar toolBar, CenterPanel centerPanel, MenuBar menuBar) {
    	this.toolBar = toolBar;
    	this.centerPanel = centerPanel;
		bottomPanel = centerPanel.getBottomPanel();
		table = centerPanel.getTable();
		this.menuBar = menuBar;
		textArea = bottomPanel.getTextArea();
    	
    	setLayout(new BorderLayout());
        taskPane = new JTaskPane();
        taskPane.setBackground(new Color(117, 150, 227));
        add(taskPane, BorderLayout.CENTER);
        initGUI();
        
    }
    
    private void initGUI() {
    	JButton saveTask = addTask("Zapisz jako", "diskette.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	centerPanel.saveTable(table, textArea);
            }
        });
        JButton printTask = addTask("Drukuj", "printer.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	menuBar.printTable(table);
            }
        });
        addTaskGroup("Zadania na plikach", new JButton[] {saveTask, printTask});  
        
        JButton sumTask = addTask("Oblicz sume z tabeli", "symbol.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int sum = bottomPanel.getCalculationController().getCalculationModel().getSum(table);
    			textArea.setText("Suma elementów wynosi: " + sum);
            }
        });
        JButton avgTask = addTask("Oblicz średnią z tabeli", "average.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	double average = bottomPanel.getCalculationController().getCalculationModel().getAverage(table);
    			textArea.setText("Średnia wartość elementów wynosi: " + average);
            }
        });
        JButton minTask = addTask("Oblicz minimalną wartość z tabeli", "min.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int min = bottomPanel.getCalculationController().getCalculationModel().getMin(table);
    			textArea.setText("Najmniejsza wartość w tabeli to: " + min);
            }
        });
        JButton maxTask = addTask("Oblicz maksymalną wartość z tabeli", "plus.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int max = bottomPanel.getCalculationController().getCalculationModel().getMax(table);
    			textArea.setText("Największa wartość w tabeli to: " + max);
            }
        });
        addTaskGroup("Zadania na tabeli", new JButton[] {sumTask, avgTask, minTask, maxTask}); 
        
        JButton helpTask = addTask("Pomoc", "question-mark.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 menuBar.showHelpDialog();
            }
        });
        JButton infoTask = addTask("Informacje", "letter-i.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	menuBar.showInfoDialog();
            }
        });
        addTaskGroup("Zobacz również", new JButton[] {helpTask, infoTask}); 
    }
    
    public void addTaskGroup(String groupName, JButton[] buttons) {
    	 JTaskPaneGroup group = new JTaskPaneGroup();
         group.setTitle(groupName);
         for (JButton button : buttons) {
        	 group.add(button);
         }
         taskPane.add(group);
    }

    public JButton addTask(String taskName, String iconResource, ActionListener listener) {
        JButton taskButton = new JButton(taskName);
        ImageIcon icon = toolBar.getResource(iconResource);
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(ICON_WIDTH, -1, java.awt.Image.SCALE_SMOOTH));
        taskButton.setIcon(scaledIcon);
        taskButton.addActionListener(listener);
        
        return taskButton;
    }

    public void clearTasks() {
        taskPane.removeAll();
    }
}
