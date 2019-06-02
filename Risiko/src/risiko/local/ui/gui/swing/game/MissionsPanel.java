package risiko.local.ui.gui.swing.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.valueobjects.missions.Mission;

public class MissionsPanel extends JPanel{
	JLabel nachricht;
	private int aktuellerSpieler;
	Risiko risiko;
	String mission;
	
	public MissionsPanel(Risiko risiko, int parentWidth, int parentHeight) {
	this.risiko = risiko;
		setUpUI(parentWidth,parentHeight);
	}
	
	// Hinweis: Gewinner wird geprueft mit risiko.einerHatGewonnen() bei Buttons-Klick waehrrend Spiel
	
	private void setUpUI(int parentWidth, int parentHeight) {
		
		nachricht = new JLabel("Mission");
		this.add(nachricht);
		this.setSize(parentWidth/4,parentHeight);
		this.setPreferredSize(new Dimension(parentWidth/4,parentHeight));
		
	}
	public void setNachricht(String message) {
		nachricht.setText(message);
	}
	public void setAktuellerSpieler(int spielerID) {
		this.aktuellerSpieler = spielerID;	
		updateMission();
	}
	public void updateMission(){
		mission = risiko.getMissionVonSpieler(aktuellerSpieler);
		nachricht.setText(mission);
	}
	
}
