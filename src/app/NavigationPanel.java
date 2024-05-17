package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;


public class NavigationPanel extends JPanel {
	private JTaskPane taskPane;

    public NavigationPanel() {
    	setLayout(new BorderLayout());
        taskPane = new JTaskPane();
        taskPane.setBackground(new Color(117, 150, 227));
        add(taskPane, BorderLayout.CENTER);
        
        addTask("Task 1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Wybrano zadanie 1");
            }
        });
        addTask("Task 2", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Wybrano zadanie 2");
            }
        });
       
    }

    public void addTask(String taskName, ActionListener listener) {
        JTaskPaneGroup group = new JTaskPaneGroup();
        group.setTitle(taskName);
        JButton taskButton = new JButton(taskName);
        taskButton.addActionListener(listener);
        group.add(taskButton);
        taskPane.add(group);
    }

    public void clearTasks() {
        taskPane.removeAll();
    }
}
