package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CalculationView extends JPanel {
	private static final String[] CALCULATIONS = {"Suma","Średnia","Minimum","Maksimum"};
	
    private JTextArea textArea;
    private JComboBox<String> calculationComboBox;
    private JButton resolveButton;

    public CalculationView(MyWindow myWindow) {
        setLayout(new BorderLayout());

        JPanel calcPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Obliczenia");
        resolveButton = new JButton("Oblicz");

        calculationComboBox = new JComboBox<>(CALCULATIONS);
        calcPanel.add(label);
        calcPanel.add(calculationComboBox);
        calcPanel.add(resolveButton);

        JPanel textPanel = new JPanel(new FlowLayout());
        textPanel.setBorder(BorderFactory.createTitledBorder(null, "Uzyskany wynik", TitledBorder.CENTER, TitledBorder.TOP));

        textArea = new JTextArea(4, 20);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(myWindow.getWidth() - 400, 100));
        Border textBorder = BorderFactory.createLineBorder(Color.BLACK);
        textArea.setBorder(textBorder);

        textPanel.add(scrollPane);

        add(calcPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JComboBox<String> getCalculationComboBox() {
        return calculationComboBox;
    }

    public JButton getResolveButton() {
        return resolveButton;
    }
}
