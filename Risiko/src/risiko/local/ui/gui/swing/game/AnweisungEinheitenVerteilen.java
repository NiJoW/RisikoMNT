package risiko.local.ui.gui.swing.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;

public class AnweisungEinheitenVerteilen extends JPanel {

	Risiko risiko;
	
	public AnweisungEinheitenVerteilen(Risiko risiko, int aktuellerSpieler) {
		this.risiko = risiko;
		setUpUI(aktuellerSpieler);
	}
	
	private void setUpUI(int aktuellerSpieler) {
		int verteilbareEinheiten = risiko.getVerteilbareEinheiten(aktuellerSpieler);
		JLabel phasenText = new JLabel("Du darfst " + verteilbareEinheiten + " Einheiten verteilen. Auf welche Provinzen willst du sie setzten?");
		this.add(phasenText);
	}
	
}
