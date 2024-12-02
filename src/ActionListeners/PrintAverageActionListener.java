package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import finalProject.DoubleList;

import java.util.concurrent.CompletableFuture;

public class PrintAverageActionListener implements ActionListener {
	JScrollPane scrollPane;
	DefaultTableModel model;
	JLabel best;
	
	public void setParams(JScrollPane scrollPassed, DefaultTableModel model, JLabel best) {
	    this.scrollPane = scrollPassed;
	    this.model = model;
	    this.best = best;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
	    // Limpiar el modelo antes de agregar nuevos datos
		this.model.setColumnCount(0);
	    this.model.setRowCount(0);
	    
	    this.model.addColumn("Opción");
        this.model.addColumn("Tiempo Total");
        this.model.addColumn("Tiempo de Espera");
        this.model.addColumn("Indice de Servicio");
        
        @SuppressWarnings("unused")
		CompletableFuture<Void> espera = CompletableFuture.runAsync(() ->{
        	
        	try {
        		Thread.sleep(20);
        	} catch (InterruptedException error) {
        		System.out.println("error");
        	}
        	
        	DoubleList list = null;
        	String name = null;
        	String bestName = null;
        	float bestProm = 0;
        	for (int i = 0; i<3; i++) {
        		switch(i) {
        		case 0:
        			list = DoubleList.queue;
        			name="Cola";
        			break;
        		case 1:
        			list = DoubleList.stack;
        			name="Pila";
        			break;
        		case 2:
        			list = DoubleList.rr;
        			name="RR";
        			break;
        		}
        		float[] avg = list.getAvg();
        		this.model.addRow(new Object[] {name, avg[0], avg[1], avg[2]});
        		if (bestProm==0 || bestProm < avg[2]) {
        			bestProm = avg[2];
        			bestName = name;
        		}
        	}
        	if (bestProm!=0) {
        		best.setText("El mejor es: " + bestName);
        		best.revalidate();
        		best.repaint();	
        	}
        });
        
        
	}

}
