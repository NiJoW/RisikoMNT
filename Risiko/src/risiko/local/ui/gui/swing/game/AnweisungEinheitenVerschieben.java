package risiko.local.ui.gui.swing.game;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnweisungEinheitenVerschieben extends JPanel{
	public AnweisungEinheitenVerschieben() {
		setUpUI();
	}
	
	private void setUpUI() {
		JLabel phasenText = new JLabel("Wohin möchtest du die Einheiten verschieben?");
		this.add(phasenText);
		this.setVisible(false);	
	}
}
