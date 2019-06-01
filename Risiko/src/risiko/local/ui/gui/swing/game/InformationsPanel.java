package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class InformationsPanel extends JPanel {

	Risiko risiko;
	int phasenID = 1;
	int aktuellerSpieler;
	int height = 200;
	
	GridBagConstraints c;
	GridBagLayout layout;
	
	MissionsPanel missionsPanel;
	AnweisungsPanel anweisungsPanel;
	EinheitenKartenPanel einheitenKartenPanel;
	
	


	
	
	
	public InformationsPanel(Risiko risiko, int aktuellerSpieler) {
		this.risiko = risiko;
		this.aktuellerSpieler = aktuellerSpieler;
		//setUpUI();
	}

	public void setUpUI() {	
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(this.getParent().getWidth(), height));
		
		layout = new GridBagLayout();
		this.setLayout(layout);
		c = new GridBagConstraints();
		
		missionsPanel = new MissionsPanel(this.getWidth(),height);
		einheitenKartenPanel = new EinheitenKartenPanel(this.getWidth(),height);
		anweisungsPanel = new AnweisungsPanel(this.getWidth(),height);
		
		c.gridx = 0;
		layout.setConstraints(missionsPanel, c);
		this.add(missionsPanel);
		
		c.gridx = 1;
		layout.setConstraints(anweisungsPanel, c);
		this.add(anweisungsPanel);
		
		c.gridx = 2;
		layout.setConstraints(einheitenKartenPanel, c);
		this.add(einheitenKartenPanel);
	
		
		
		//TODO: Abtrennung fuer Tauschekarten
	
	}


	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}
	
	public void setAktuellerSpieler(int spielerID) {
		aktuellerSpieler = spielerID;
	}

	public void setNachricht(String message) {
		anweisungsPanel.setNachricht(message);
	}
	
	public void setMissionsNachricht(String message) {
		missionsPanel.setNachricht(message);
	}
	
	public void setEinheitenKartenNachricht(String message) {
		einheitenKartenPanel.setNachricht(message);
	}
}
