package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableController implements ActionListener {
	 private MyTableModel model;
	 private CenterPanel view;
	    
	 public TableController(MyTableModel model, CenterPanel view) {
	     this.model = model;
	     this.view = view;

	        // Dodanie nasłuchiwania zdarzeń do przycisków
	     //view.getAddButton().addActionListener(this);
	     //view.getResetButton().addActionListener(this);
	     //view.getFillButton().addActionListener(this);
	     //view.getSaveValuesButton().addActionListener(this);
	    }
	    
	  @Override
	  public void actionPerformed(ActionEvent e) {
	     //if (e.getSource() == view.getAddButton()) {
	            // Obsługa dodawania do tabeli
	     //} else if (e.getSource() == view.getResetButton()) {
	            // Obsługa resetowania tabeli
	     //} else if (e.getSource() == view.getFillButton()) {
	            // Obsługa wypełniania tabeli
	     //} else if (e.getSource() == view.getSaveValuesButton()) {
	            // Obsługa zapisu danych z tabeli
	     //}
	  }
}
