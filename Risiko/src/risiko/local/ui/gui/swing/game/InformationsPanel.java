package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.KartenEintauschenListener;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class InformationsPanel extends JPanel {

	Risiko risiko;
	int phasenID = 1;
	int aktuellerSpieler;
	int height = 150;
	
	GridBagConstraints c;
	GridLayout layout;
	
	MissionsPanel missionsPanel;
	AnweisungsPanel anweisungsPanel;
	EinheitenKartenPanel einheitenKartenPanel;
	KartenEintauschenListener kartenEintauschenListener;
	
	public InformationsPanel(Risiko risiko, int aktuellerSpieler, KartenEintauschenListener kartenEintauschenListener) {
		this.risiko = risiko;
		this.aktuellerSpieler = aktuellerSpieler;
		this.kartenEintauschenListener = kartenEintauschenListener;
	//	this.setVisible(true);
		//setUpUI();
	}

	public void setUpUI() {	
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(this.getParent().getWidth(), height));
		
		layout = new GridLayout(1, 3);
		this.setLayout(layout);
		
		missionsPanel = new MissionsPanel(risiko, this.getWidth(), height);
		einheitenKartenPanel = new EinheitenKartenPanel(risiko, aktuellerSpieler, this.getWidth(), height, kartenEintauschenListener);
		anweisungsPanel = new AnweisungsPanel(this.getWidth(), height);
		
		this.add(missionsPanel);
		this.add(anweisungsPanel);
		this.add(einheitenKartenPanel);
	
		
		
		//TODO: Abtrennung fuer Tauschekarten
	
	}


	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}
	
	public void setAktuellerSpieler(int spielerID) {
		aktuellerSpieler = spielerID;
		missionsPanel.setAktuellerSpieler(spielerID);
		einheitenKartenPanel.setAktuellerSpieler(spielerID);
	}

	public void setNachricht(String message) {
		anweisungsPanel.setNachricht(message);
	}
	
	//TODO: aktuellerspieler zentral erhoehen
	public void setMissionsNachricht(int aktuellerSpieler) {
		System.out.println("Spieler: " + aktuellerSpieler);
		if(aktuellerSpieler == (risiko.getSpielerAnzahl()-1)) {
			missionsPanel.setNachricht(0);
		} else {
			missionsPanel.setNachricht(aktuellerSpieler+1);
		}
	}
	
	public void setEinheitenKartenNachricht(int aktuellerSpieler) {
		einheitenKartenPanel.setNachricht(aktuellerSpieler, risiko);
	}

	public void updateInfoPanel(int aktuellerSpieler) {
		if(aktuellerSpieler == (risiko.getSpielerAnzahl()-1)) {
			setEinheitenKartenNachricht(0);
			missionsPanel.setNachricht(0);
		} else {
			setEinheitenKartenNachricht(aktuellerSpieler+1);
			missionsPanel.setNachricht(aktuellerSpieler+1);
		}
	}

	public void pruefeTauschen() {
		einheitenKartenPanel.pruefeTauschen();
	}

	public void toggleTauschen(boolean status) {
		einheitenKartenPanel.toggleTauschen(status);
	}
}
