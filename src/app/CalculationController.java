package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationController {
    private CalculationModel model;
    private CalculationView view;

    public CalculationController(CalculationModel model, CalculationView view) {
        this.model = model;
        this.view = view;

        this.view.getResolveButton().addActionListener(new ResolveButtonListener());
    }
    
    public CalculationModel getCalculationModel() {
    	return model;
    }
    
    public CalculationView getCalculationView() {
    	return view;
    }

    class ResolveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getCalculationComboBox().getSelectedIndex();
            switch (index) {
                case 0:
                    int sum = model.getSum(model.getTable());
                    view.getTextArea().setText("Suma elementów wynosi: " + sum);
                    break;
                case 1:
                    double average = model.getAverage(model.getTable());
                    view.getTextArea().setText("Średnia wartość elementów wynosi: " + average);
                    break;
                case 2:
                    int min = model.getMin(model.getTable());
                    view.getTextArea().setText("Najmniejsza wartość w tabeli to: " + min);
                    break;
                case 3:
                    int max = model.getMax(model.getTable());
                    view.getTextArea().setText("Największa wartość w tabeli to: " + max);
                    break;
            }
        }
    }
}
