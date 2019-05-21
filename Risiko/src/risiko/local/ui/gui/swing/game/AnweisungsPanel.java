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
	int phasenID = 1;
	int aktuellerSpieler;
	AnweisungEinheitenVerteilen phaseEins;
	AnweisungAngriff phaseZwei;
	AnweisungEinheitenVerschieben phaseDrei;
	
	
	public AnweisungsPanel(Risiko risiko, int screenWidth, int screenHeight) {
		this.risiko = risiko;
		initialize(screenWidth, screenHeight);
	}

	private void initialize(int screenWidth, int screenHeight) {	
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension pageEndSize = new Dimension(screenWidth, screenHeight/5);
		this.setMinimumSize(pageEndSize);
		this.setPreferredSize(pageEndSize);
		
		phaseEins = new AnweisungEinheitenVerteilen(risiko, 0);
		this.add(phaseEins);
		phaseZwei = new AnweisungAngriff();
		this.add(phaseZwei);
		phaseDrei = new AnweisungEinheitenVerschieben();
		this.add(phaseDrei);

		
		
		
		//TODO: Abtrennung fuer Tauschekarten
	
	}
	
	private void updatePanel() {
		//Anweisung an Phase anpassen
		switch(phasenID) {
			case 1:
				phaseDrei.setVisible(false);
				phaseEins.setVisible(true);
				break;
			case 2:
				phaseEins.setVisible(false);
				phaseZwei.setVisible(true);
				break;
			case 3:
				phaseZwei.setVisible(false);
				phaseDrei.setVisible(true);
				break;		
		}
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
		updatePanel();
	}
	
	public void setAktuellerSpieler(int spielerID) {
		aktuellerSpieler = spielerID;
	}
}
