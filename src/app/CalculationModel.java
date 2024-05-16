package app;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class CalculationModel {
    private JTable table;

    public CalculationModel(JTable table) {
        this.table = table;
    }
    
    public JTable getTable() {
    	return table;
    }

    public int getSum(JTable table) {
        int sum = 0;
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                sum += (int) model.getValueAt(i, j);
            }
        }

        return sum;
    }

    public double getAverage(JTable table) {
        int sum = getSum(table);
        double average = (double) sum / (table.getRowCount() * table.getColumnCount());

        return average;
    }

    public int getMin(JTable table) {
        int min = Integer.MAX_VALUE;
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                int value = (int) model.getValueAt(i, j);
                if (value < min) {
                    min = value;
                }
            }
        }

        return min;
    }

    public int getMax(JTable table) {
        int max = Integer.MIN_VALUE;
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                int value = (int) model.getValueAt(i, j);
                if (value > max) {
                    max = value;
                }
            }
        }

        return max;
    }
}
