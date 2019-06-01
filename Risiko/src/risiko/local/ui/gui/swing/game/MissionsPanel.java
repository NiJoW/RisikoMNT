package risiko.local.ui.gui.swing.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MissionsPanel extends JPanel{
	JLabel nachricht;
	
	public MissionsPanel(int parentWidth, int parentHeight) {
		setUpUI(parentWidth,parentHeight);
	}
	private void setUpUI(int parentWidth, int parentHeight) {
		this.setSize(parentWidth/4,parentHeight);
		this.setPreferredSize(new Dimension(parentWidth/4,parentHeight));
		nachricht = new JLabel("Mission");
		this.add(nachricht);
		
	}
	public void setNachricht(String message) {
		nachricht.setText(message);
	}
}
