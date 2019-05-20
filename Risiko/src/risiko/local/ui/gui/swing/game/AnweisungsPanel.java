package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;

public class AnweisungsPanel extends JPanel {

	Risiko risiko;
	int phasenID;
	int aktuellerSpieler;
	
	public AnweisungsPanel(Risiko risiko, int screenWidth, int screenHeight) {
		this.risiko = risiko;
		initialize(screenWidth, screenHeight);
	}

	private void initialize(int screenWidth, int screenHeight) {
		int verteilbareEinheiten = risiko.getVerteilbareEinheiten(aktuellerSpieler);
		JLabel phasenText = new JLabel("Du darfst " + verteilbareEinheiten + " Einheiten verteilen. Auf welche Provinzen willst du sie setzten?");
		this.add(phasenText);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension pageEndSize = new Dimension(screenWidth, screenHeight/5);
		this.setMinimumSize(pageEndSize);
		this.setPreferredSize(pageEndSize);
		//TODO: Abtrennung fuer Tauschekarten
	}
	
	private void updatePanel() {
		//Anweisung an Phase anpassen
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
		updatePanel();
	}
	
	public void setAktuellerSpieler(int spielerID) {
		aktuellerSpieler = spielerID;
	}
}
