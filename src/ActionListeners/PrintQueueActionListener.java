package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import finalProject.DoubleList;
import finalProject.Node;

public class PrintQueueActionListener implements ActionListener {
	JTextField filePathField;
	JScrollPane scrollPane;
	DefaultTableModel model;
	DoubleList list;
	JTextField quantumField;
	
	public void setParams(JTextField filePath, JScrollPane scrollPassed, DefaultTableModel model, DoubleList type){
		setParams(filePath, scrollPassed, model, type, null);
	}
	
	public void setParams(JTextField filePath, JScrollPane scrollPassed, DefaultTableModel model, DoubleList type, JTextField quantum) {
	    this.filePathField = filePath;
	    this.scrollPane = scrollPassed;
	    this.model = model;
	    this.list = type;
	    this.quantumField = quantum;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
	    // Limpiar el modelo antes de agregar nuevos datos
		this.model.setColumnCount(0);
	    this.model.setRowCount(0);
	    
	    DoubleList rr = DoubleList.rr;
	    try {
	    	int j = Integer.parseInt(quantumField.getText());
	    	if (rr.getQuantum() != j) {
	    		if (j < 1) {
	                System.err.println("Invalid input: El valor debe ser mayor o igual a 1.");
	            } else {
	                rr.setQuantum(j);
	                rr.calculateRr();
	            }
	    	}
	    } catch (NumberFormatException error) {
            System.err.println("Invalid input: " + quantumField.getText() + " is not a valid integer.");
        }
	   
	    
        this.model.addColumn("Tiempo de ejecución");
        this.model.addColumn("Tiempo de inicio");
        this.model.addColumn("Tiempo Final");
        this.model.addColumn("Tiempo Total");
        this.model.addColumn("Tiempo de Espera");
        this.model.addColumn("Índice de Rendimiento");
        
        Node current = list.getHead();
	    for (int i = 0; i < list.getLength(); i++) {
	        int[] values = current.getValues();
	        this.model.addRow(new Object[]{values[0], values[1], values[2], values[3], values[4], current.getServiceIndex()});
	        current=current.getNext();
	    }
	}

}
