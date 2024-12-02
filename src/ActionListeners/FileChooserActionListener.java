package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class FileChooserActionListener implements ActionListener {
	JTextField filePathField;
	
	public void setJTextField(JTextField filePath){
		this.filePathField = filePath;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        File currentDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(currentDirectory);
        
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }
	
}
