package ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

public class ChangeBoundsActionListener implements ActionListener {
	JScrollPane scrollPane;
	int x, y, width, height;
	
	public void setParams(JScrollPane scrollPane ,int x, int y, int width, int height) {
		this.scrollPane = scrollPane;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		scrollPane.setBounds(x, y, width, height);
	    scrollPane.revalidate();
	    scrollPane.repaint();
	}
}
