package finalProject;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ActionListeners.*;
import javax.swing.JLabel;

public class main extends JFrame {
	// Identificador único para serialización de la clase ??
	private static final long serialVersionUID = 1L;
	// Panel contenido en la ventana
    
    private DoubleList queue = DoubleList.queue;
    private DoubleList stack = DoubleList.stack;
    private DoubleList rr = DoubleList.rr;
    private JTable table;
    private DefaultTableModel model;
    private JTextField textQuantum;
    private JTable resumenTable;
    private DefaultTableModel resumenModel;

    public main() {
        setTitle("Buscador de Archivos");
        setBounds(100, 100, 1200, 550);
        // La aplicación se cierra al cerrar la pestaña
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        

        JTextField filePathField = new JTextField(40);
        filePathField.setBounds(22, 30, 250, 30);
        getContentPane().add(filePathField);
        
        JButton searchButton = new JButton("Buscar Archivo");
        searchButton.setBounds(282, 30, 130, 30);
        getContentPane().add(searchButton);
        
        FileChooserActionListener searchListener = new FileChooserActionListener();
        searchListener.setJTextField(filePathField);
        searchButton.addActionListener(searchListener);
        
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(22, 71, 0, 0);
        getContentPane().add(scrollPane);
         
        JButton csvToLists = new JButton("Asignar Valores");
        csvToLists.setBounds(422, 30, 130, 30);
        getContentPane().add(csvToLists);
        
        SetValuesActionListener valuesListener = new SetValuesActionListener();
        valuesListener.setParams(filePathField, scrollPane, model);
        csvToLists.addActionListener(valuesListener);
        
        ChangeBoundsActionListener changeBounds = new ChangeBoundsActionListener();
        changeBounds.setParams(scrollPane, 22, 71, 391,230);
        csvToLists.addActionListener(changeBounds);
        
        JButton queueButton = new JButton("Calcular Cola");
        queueButton.setBounds(562, 30, 130, 30);
        getContentPane().add(queueButton);
        
        textQuantum = new JTextField();
        textQuantum.setText("1");
        textQuantum.setBounds(986, 30, 121, 30);
        getContentPane().add(textQuantum);
        textQuantum.setColumns(1);
        
        PrintQueueActionListener queueAction = new PrintQueueActionListener();
        queueButton.addActionListener(queueAction);
        queueAction.setParams(filePathField, scrollPane, model, queue, textQuantum);
        
        ChangeBoundsActionListener changeBoundsQueue = new ChangeBoundsActionListener();
        changeBoundsQueue.setParams(scrollPane, 22, 71, 900,230);
        queueButton.addActionListener(changeBoundsQueue);
        
        JButton stackButton = new JButton("Calcular Stack");
        stackButton.setBounds(702, 30, 130, 30);
        getContentPane().add(stackButton);
        
        PrintQueueActionListener stackAction = new PrintQueueActionListener();
        stackButton.addActionListener(stackAction);
        stackAction.setParams(filePathField, scrollPane, model, stack, textQuantum);
        stackButton.addActionListener(changeBoundsQueue);
        
        
        
        JButton rrButton = new JButton("Calcular RR");
        rrButton.setBounds(846, 30, 130, 30);
        getContentPane().add(rrButton);
        
        PrintQueueActionListener rrAction = new PrintQueueActionListener();
        rrAction.setParams(filePathField, scrollPane, model, rr, textQuantum);
        rrButton.addActionListener(rrAction);
        
        
        JLabel quatumLabel = new JLabel("Quantum (default: 1)");
        quatumLabel.setBounds(986, 11, 160, 14);
        getContentPane().add(quatumLabel);
        rrButton.addActionListener(changeBoundsQueue);
        
        resumenModel = new DefaultTableModel();
        resumenTable = new JTable(resumenModel);
        JScrollPane resumenScroll = new JScrollPane(resumenTable);
        resumenScroll.setBounds(22, 321, 0, 0);
        getContentPane().add(resumenScroll);
        
        JLabel best = new JLabel();
        best.setBounds(450, 350, 160, 14);
        getContentPane().add(best);
        
        PrintAverageActionListener average = new PrintAverageActionListener();
        average.setParams(resumenScroll, resumenModel, best);
        queueButton.addActionListener(average);
        
        stackButton.addActionListener(average);
        
        rrButton.addActionListener(average);
        
        ChangeBoundsActionListener changeBoundsResumen = new ChangeBoundsActionListener();
        changeBoundsResumen.setParams(resumenScroll, 22, 321, 380 , 90);
        queueButton.addActionListener(changeBoundsResumen);
        stackButton.addActionListener(changeBoundsResumen);
        rrButton.addActionListener(changeBoundsResumen);
        
    }
    
    public static void main(String[] args) {
    	// Se garantiza creación en el hilo de Swing
    	 EventQueue.invokeLater(new Runnable() {
    	        public void run() {
    	            try {
    	            	MusicPlayer player = MusicPlayer.player;
    	            	player.reproducirMusica();
    	                main frame = new main();
    	                frame.setVisible(true);
    	            } catch (Exception e) {
    	                e.printStackTrace();
    	            }
    	        }
    	    });
    }
}
