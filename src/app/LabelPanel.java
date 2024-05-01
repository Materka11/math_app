package app;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class LabelPanel extends JPanel {
	private JLabel numberInputLabel, rowSliderLabel, colSliderLabel; 
	private JTextField numberInput;
	private JSlider rowSlider, colSlider;
	
	public LabelPanel() {
		initGUI();
		
		createLabelPanel(
			new JLabel[] {numberInputLabel, rowSliderLabel, colSliderLabel}, numberInput, 
			new JSlider[] {rowSlider, colSlider}
		);
	}
	
	private void initGUI() {
		numberInputLabel = createlabel("Wprowadź liczbę");
		numberInput = createTextInput("0", 100, 20);
		
		rowSliderLabel = createlabel("Numer wiersza");
		rowSlider = createSlider(1, 5, 3, 100, 40);
		
		colSliderLabel = createlabel("Numer kolumny");
		colSlider = createSlider(1, 5, 3, 100, 40);
	}
	
	private JLabel createlabel(String text) {
		JLabel label = new JLabel(text);
		
		return label;
	}
	
	private JTextField createTextInput(String defaultValue, int width, int height) {
		JTextField input = new JTextField();
		input.setText(defaultValue);
		input.setPreferredSize(new Dimension(width, height));
		
		return input;
	}
	
	private JSlider createSlider(int minValue, int maxValue, int value, int width, int height) {
		JSlider slider = new JSlider(minValue, maxValue, value);
		slider.setPreferredSize(new Dimension(width, height));
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		return slider;
	}
	
	private void createLabelPanel(JLabel[] labels, JTextField input, JSlider[] sliders) {
		JPanel labelPanel = new JPanel(new FlowLayout());
		
		labelPanel.add(labels[0]);
		labelPanel.add(input);
		labelPanel.add(labels[1]);
		labelPanel.add(sliders[0]);
		labelPanel.add(labels[2]);
		labelPanel.add(sliders[1]);
		
		add(labelPanel);
	}
	
	public JSlider getColSlider() {
		return colSlider;
	}

	public JSlider getRowSlider() {
		return rowSlider;
	}
	
	public JTextField getNumberInput() {
		return numberInput;
	}
}
