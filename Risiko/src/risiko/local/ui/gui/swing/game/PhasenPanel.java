package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhasenPanel extends JPanel {

	Risiko risiko;
	AnweisungsPanel anweisungsPanel;
	
	GridBagConstraints c;
	PhaseEinheitenVerteilen phaseEins;
	PhaseAngriff phaseZwei;
	PhaseEinheitenVerschieben phaseDrei;
	int aktuellerSpieler;

	int phasenID;
	PhaseBeendenListener phaseBeendenListener;

	

	public PhasenPanel(Risiko risiko, PhaseBeendenListener phaseBeendenListener, AnweisungsPanel anweisungsPanel) {
		this.risiko = risiko;
		this.anweisungsPanel = anweisungsPanel;
		this.phaseBeendenListener = phaseBeendenListener;
		setUpUI();
		ereignisErzeugt();
	}

	private void setUpUI() {
		phaseEins = new PhaseEinheitenVerteilen(risiko, anweisungsPanel, aktuellerSpieler);
		this.add(phaseEins);
		phaseZwei = new PhaseAngriff(risiko, anweisungsPanel, aktuellerSpieler);
		this.add(phaseZwei);
		phaseDrei = new PhaseEinheitenVerschieben();
		this.add(phaseDrei);

		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}


	public void ereignisErzeugt() {
		phaseEins.setUpEvents(phaseBeendenListener, phaseZwei);
		phaseZwei.setUpEvents(phaseBeendenListener, phaseDrei);
		phaseDrei.setUpEvents(phaseBeendenListener, phaseEins);
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}

	public void setClickedProvinz(int provinzIDByColor) {
		System.out.println("phase: " + this.phasenID);
		switch(this.phasenID) {
		case 1: 
			phaseEins.setProvinz(provinzIDByColor);
			break;
		case 2:
			phaseZwei.setProvinz(provinzIDByColor);
			break;
		case 3:
			phaseDrei.setProvinz(provinzIDByColor);
			break;
		}
	}

	public void setAktuellerSpieler(int spielerID) {
		aktuellerSpieler = spielerID;
	}

}
