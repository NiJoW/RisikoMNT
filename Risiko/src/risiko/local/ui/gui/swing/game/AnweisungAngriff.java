package risiko.local.ui.gui.swing.game;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnweisungAngriff extends JPanel{
	public AnweisungAngriff() {
		setUpUI();
	}
	
	private void setUpUI() {
		JLabel phasenText = new JLabel("Welches Land möchtest du angreifen?");
		this.add(phasenText);
		this.setVisible(false);	
	}
}
