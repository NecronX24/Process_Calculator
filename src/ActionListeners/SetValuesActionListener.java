package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import finalProject.DoubleList;
import finalProject.CsvReader;
import finalProject.Node;

public class SetValuesActionListener implements ActionListener {
	JTextField filePathField;
	JScrollPane scrollPane;
	DefaultTableModel model;
	DoubleList queue = DoubleList.queue;
	DoubleList stack = DoubleList.stack;
	DoubleList rr = DoubleList.rr;
	
	public void setParams(JTextField filePath, JScrollPane scrollPassed, DefaultTableModel model){
		this.filePathField = filePath;
		this.scrollPane = scrollPassed;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    String filePath = filePathField.getText();
	    CsvReader.reader(queue, stack, rr, filePath);

	    // Limpiar el modelo antes de agregar nuevos datos
	    this.model.setColumnCount(0);
	    this.model.setRowCount(0);
	    
        this.model.addColumn("Tiempo de ejecución");
        this.model.addColumn("Tiempo de inicio");
        
        Node current = queue.getHead();
	    for (int i = 0; i < queue.getLength(); i++) {
	        this.model.addRow(new Object[]{current.getTime(), current.getInitialTime()});
	        current = current.getNext();
	    }
	    queue.calculateQueue();
	    stack.calculateStack();
	    rr.calculateRr();
	}
}