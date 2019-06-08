package risiko.local.ui.gui.swing.game;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.valueobjects.missions.Mission;

public class MissionsPanel extends JPanel{
	private JLabel nachricht;
	private int aktuellerSpieler;
	private Risiko risiko;
	private String mission;
	
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
	public void setNachricht(int aktuellerSpieler) {
		mission = risiko.getMissionVonSpieler(aktuellerSpieler);
		nachricht.setText(mission);
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
