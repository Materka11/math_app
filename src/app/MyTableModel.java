package app;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	public MyTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
}
