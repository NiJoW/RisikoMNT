package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnweisungsPanel extends JPanel{
	
	private JLabel nachricht;
	
	public AnweisungsPanel(int parentWidth, int parentHeight) {
		setUpUI(parentWidth,parentHeight);
	}
	private void setUpUI(int parentWidth, int parentHeight) {
		System.out.println(parentWidth/2);
		
		nachricht = new JLabel(" ");
		this.add(nachricht);
		this.setSize(parentWidth/2,parentHeight);
		this.setPreferredSize(new Dimension(parentWidth/2,parentHeight));
		
	}
	public void setNachricht(String message) {
		nachricht.setText(message);
		nachricht.setForeground(Color.red);
	}

}