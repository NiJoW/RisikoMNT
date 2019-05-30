package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	KartenPanel kartenPanel;

	

	public PhasenPanel(Risiko risiko, PhaseBeendenListener phaseBeendenListener, AnweisungsPanel anweisungsPanel, KartenPanel kartenPanel) {
		this.risiko = risiko;
		this.anweisungsPanel = anweisungsPanel;
		this.kartenPanel = kartenPanel;
		this.phaseBeendenListener = phaseBeendenListener;
		setUpUI();
		
		ereignisErzeugt();
	}

	private void setUpUI() {

		phaseEins = new PhaseEinheitenVerteilen(risiko, anweisungsPanel, aktuellerSpieler, new InitialeRundeBeendet());
		this.add(phaseEins);
		phaseZwei = new PhaseAngriff(risiko, anweisungsPanel, aktuellerSpieler);
		this.add(phaseZwei);
		phaseDrei = new PhaseEinheitenVerschieben();
		this.add(phaseDrei);

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		// TODO: nur wenn neues Spiel, nicht bei geladenem
		intitialeEinheitenVerteilen(0);
	}


	private void intitialeEinheitenVerteilen(int spieler) {
		// TODO Auto-generated method stub
		int einheitenSetztenRunden = risiko.getSpielerAnzahl(); 
		if(spieler<einheitenSetztenRunden) {
			phaseEins.initialesVerteilen(spieler);
			kartenPanel.setAktuellerSpieler(spieler);
		}else {
			kartenPanel.setAktuellerSpieler(0);
			phaseEins.beginneSpiel(0);
		}
		
		
	}
	
	public class InitialeRundeBeendet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("InitialeListener wurde aktiviert");
			int nextSpieler = Integer.parseInt(e.getActionCommand());
			intitialeEinheitenVerteilen(nextSpieler);
		}
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
